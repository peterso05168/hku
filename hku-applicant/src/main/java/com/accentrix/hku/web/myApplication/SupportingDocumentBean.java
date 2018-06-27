package com.accentrix.hku.web.myApplication;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.Serializable;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import org.apache.shiro.util.CollectionUtils;
import org.primefaces.event.FileUploadEvent;
import org.primefaces.model.DefaultStreamedContent;
import org.primefaces.model.StreamedContent;
import org.primefaces.model.UploadedFile;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Configurable;

import com.accentrix.hku.constant.ConstantCommon;
import com.accentrix.hku.constant.Constants;
import com.accentrix.hku.service.app.ReqDocService;
import com.accentrix.hku.service.app.UploadedDocService;
import com.accentrix.hku.util.JSFUtil;
import com.accentrix.hku.util.UIUtil;
import com.accentrix.hku.vo.app.ReqDocVo;
import com.accentrix.hku.vo.app.UploadedDocVo;
import com.accentrix.hku.vo.applicant.ApplicationVo;

/**
 * @author Lonny Wei
 * @date 2018年4月9日 下午1:46:41
 * @version 1.0
 */
@ManagedBean
@ViewScoped
@Configurable(preConstruction = true)
public class SupportingDocumentBean implements Serializable {

    private static final long serialVersionUID = 1L;

    private static final Logger LOG = LoggerFactory.getLogger(SupportingDocumentBean.class);

    private static final String UPLOAD_PATH_SERVER = "/var/uploads/";

    private static final List<String> FILE_CONTENT_TYPES = Arrays.asList("application/msword",
            "application/vnd.openxmlformats-officedocument.wordprocessingml.document", "image/gif", "image/jpeg",
            "application/pdf", "application/vnd.openxmlformats-officedocument.presentationml.presentation",
            "application/vnd.ms-powerpoint", "application/vnd.openxmlformats-officedocument.spreadsheetml.sheet",
            "application/vnd.ms-excel");

    @Autowired
    private ReqDocService reqDocService;

    @Autowired
    private UploadedDocService uploadedDocService;

    private List<ReqDocVo> pisReqDocVos;

    private List<ReqDocVo> othersReqDocVos;

    private ReqDocVo reqDocVo;

    private List<UploadedFile> uploadedFiles;

    private StreamedContent downloadFile;

    private ApplicationVo applicationVo;

    @PostConstruct
    public void init() {
        applicationVo = (ApplicationVo) JSFUtil.getSessionMap().get("applicationVo");
        if (applicationVo != null) {
            pisReqDocVos = reqDocService.findByReqDocType(applicationVo.getId(), Constants.DOC_TYPE_PIS);
            othersReqDocVos = reqDocService.findByReqDocType(applicationVo.getId(), Constants.DOC_TYPE_OTHERS);
            reqDocVo = new ReqDocVo();
            uploadedFiles = new ArrayList<UploadedFile>();
        }
    }

    public void handleFileUpload(FileUploadEvent event) {
        UploadedFile uploadedFile = event.getFile();
        uploadedFiles.add(uploadedFile);
    }

    public void reuploadResetFiles() {
        uploadedFiles = new ArrayList<UploadedFile>();
    }

    private boolean validateUploadFile(UploadedFile uploadedFile) {
        boolean valid = true;
        if (uploadedFile == null) {
            UIUtil.displayErrorDialog(ConstantCommon.LOCALE_UK, Constants.FILE_IS_NULL, Constants.FILE_IS_NULL_CHI);
            valid = false;
        } else if (!FILE_CONTENT_TYPES.contains(uploadedFile.getContentType())) {
            UIUtil.displayErrorDialog(ConstantCommon.LOCALE_UK, Constants.UNSUPPORTED_FILE_FORMAT,
                    Constants.UNSUPPORTED_FILE_FORMAT);
            valid = false;
        } else if (uploadedFile.getSize() > Constants.SUPPORTED_UPLOAD_FILE_SIZE) {
            UIUtil.displayErrorDialog(ConstantCommon.LOCALE_UK, Constants.UNSUPPORTED_FILE_SIZE,
                    Constants.UNSUPPORTED_FILE_SIZE_CHI);
            valid = false;
        }
        return valid;
    }

    public void upload() {
        if (uploadedFiles.size() != 0) {
            for (UploadedFile uploadedFile : uploadedFiles) {
                if (!validateUploadFile(uploadedFile))
                    return;
            }
        } else {
            UIUtil.displayErrorDialog(ConstantCommon.LOCALE_UK, Constants.NO_FILE_IS_SELECTED,
                    Constants.NO_FILE_IS_SELECTED);
            return;
        }

        reqDocVo.setStatusCd(Constants.UPLOAD);
        reqDocService.save(reqDocVo);

        String tempPath = UPLOAD_PATH_SERVER + applicationVo.getId();
        File directory = new File(tempPath);
        if (!directory.exists())
            directory.mkdir();

        List<UploadedDocVo> vos = uploadedDocService.findByReqDocId(reqDocVo.getId());
        int fileIdx = 1;
        if (!CollectionUtils.isEmpty(vos))
            fileIdx = uploadedDocService.findByReqDocId(reqDocVo.getId()).size() + 1;
        for (UploadedFile uploadedFile : uploadedFiles) {
            UploadedDocVo uploadedDocVo = new UploadedDocVo();
            uploadedDocVo.setFileName(uploadedFile.getFileName());
            int idx = uploadedFile.getFileName().lastIndexOf(".");
            uploadedDocVo.setDisplayFileName(applicationVo.getApplicationNo() + "_" + reqDocVo.getReqDocName() + "_"
                    + new SimpleDateFormat("yyyyMMddHHmmss").format(new Date()) + "_" + fileIdx
                    + uploadedFile.getFileName().substring(idx));
            uploadedDocVo.setFilePath("");
            uploadedDocVo.setActive(true);
            uploadedDocVo.setSubmissionDate(new Date());
            uploadedDocVo.setRemark(reqDocVo.getRemark());
            uploadedDocVo.setReqDocId(reqDocVo.getId());
            uploadedDocVo = uploadedDocService.save(uploadedDocVo);

            File file = saveFileToSystemTempPath(uploadedDocVo, uploadedFile, directory);
            uploadedDocVo = uploadedDocService.get(uploadedDocVo.getId());
            uploadedDocVo.setFilePath(file.getAbsolutePath());
            uploadedDocService.save(uploadedDocVo);
            fileIdx++;
        }
        init();
        UIUtil.displaySaveSuccessDialog(ConstantCommon.LOCALE_UK);
        UIUtil.hide("supDialog");
    }

    private File saveFileToSystemTempPath(UploadedDocVo uploadedDocVo, UploadedFile uploadedFile, File directory) {
        File file = null;
        //        File newFile = null;
        FileOutputStream fos = null;
        BufferedOutputStream bos = null;
        int idx = uploadedDocVo.getFileName().lastIndexOf(".");
        try {
            file = File.createTempFile(uploadedDocVo.getId(), uploadedDocVo.getFileName().substring(idx), directory);
            //            newFile = new File(directory.getAbsolutePath() + "\\" + uploadedFile.getFileName());
            //            file.renameTo(newFile);
            fos = new FileOutputStream(file);
            bos = new BufferedOutputStream(fos);
            bos.write(uploadedFile.getContents());
        } catch (IOException e) {
            LOG.error(
                    "File.createTempFile(String prefix,String suffix) or BufferedOutputStream.write(byte[]) cause IOException",
                    e);
        } finally {
            try {
                if (fos != null)
                    fos.close();
                if (bos != null)
                    bos.close();
            } catch (IOException e) {
                LOG.error("Close FileOutputStream or BufferedOutputStream cause IOException", e);
            }
        }
        return file;
    }

    public void loadUploadDialog(ReqDocVo vo) {
        reqDocVo = vo;
    }

    public void download(ReqDocVo reqDocVo) {
        File file = new File(reqDocVo.getFilePath());
        InputStream input = null;
        try {
            input = new FileInputStream(file);
            String contentType = Files.probeContentType(Paths.get(file.getAbsolutePath()));
            downloadFile = new DefaultStreamedContent(input, contentType, reqDocVo.getFileName());
        } catch (IOException e) {
            LOG.error(e.getMessage(), e);
        }
    }

    public String formatDateYyyyMMdd(Date date) {
        return date == null ? "" : new SimpleDateFormat("yyyy-MM-dd").format(date);
    }

    public List<ReqDocVo> getPisReqDocVos() {
        return pisReqDocVos;
    }

    public void setPisReqDocVos(List<ReqDocVo> pisReqDocVos) {
        this.pisReqDocVos = pisReqDocVos;
    }

    public ReqDocVo getReqDocVo() {
        return reqDocVo;
    }

    public void setReqDocVo(ReqDocVo reqDocVo) {
        this.reqDocVo = reqDocVo;
    }

    public List<UploadedFile> getUploadedFiles() {
        return uploadedFiles;
    }

    public void setUploadedFiles(List<UploadedFile> uploadedFiles) {
        this.uploadedFiles = uploadedFiles;
    }

    public StreamedContent getDownloadFile() {
        return downloadFile;
    }

    public List<ReqDocVo> getOthersReqDocVos() {
        return othersReqDocVos;
    }

    public void setOthersReqDocVos(List<ReqDocVo> othersReqDocVos) {
        this.othersReqDocVos = othersReqDocVos;
    }

}
