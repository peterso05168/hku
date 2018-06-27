package com.accentrix.hku.service.app;

import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

import org.jsondoc.core.annotation.Api;
import org.jsondoc.core.annotation.ApiBodyObject;
import org.jsondoc.core.annotation.ApiMethod;
import org.jsondoc.core.annotation.ApiPathParam;
import org.jsondoc.core.annotation.ApiQueryParam;
import org.jsondoc.core.annotation.ApiResponseObject;
import org.jsondoc.core.pojo.ApiVerb;

import com.accentrix.hku.vo.app.UploadedDocVo;

/** 
 * @author Jaye.Lin 
 * @date 创建时间：2018年1月31日 下午2:28:26 
 * @version 1.0 
 */

@Consumes({ MediaType.APPLICATION_JSON, MediaType.TEXT_XML })
@Produces({ MediaType.APPLICATION_JSON, MediaType.TEXT_XML })
@Api(name = "Uploaded Doc Service", description = "uploadedDocService")
public interface UploadedDocService {

    @GET
    @Path("get")
    @ApiMethod(path = "/api/rest/uploadedDoc/get/", verb = ApiVerb.GET, description = "get uploadedDoc by id", produces = {
            MediaType.APPLICATION_JSON }, consumes = { MediaType.APPLICATION_JSON })
    @ApiResponseObject
    UploadedDocVo get(@ApiQueryParam(name = "id", description = "The uploadedDoc id") @QueryParam("id") String id);

    @POST
    @Path("/")
    @ApiMethod(path = "/api/rest/uploadedDoc/", verb = ApiVerb.POST, description = "save uploadedDoc", produces = {
            MediaType.APPLICATION_JSON }, consumes = { MediaType.APPLICATION_JSON })
    @ApiResponseObject
    UploadedDocVo save(@ApiBodyObject UploadedDocVo vo);

    @POST
    @Path("/save-list/")
    @ApiMethod(path = "/api/rest/uploadedDoc/save-list/", verb = ApiVerb.POST, description = "save uploadedDoc list", produces = {
            MediaType.APPLICATION_JSON }, consumes = { MediaType.APPLICATION_JSON })
    @ApiResponseObject
    List<UploadedDocVo> save(List<UploadedDocVo> vos);

    @DELETE
    @Path("/{id}/")
    @ApiMethod(path = "/api/rest/uploadedDoc/{id}/", verb = ApiVerb.DELETE, description = "delete uploadedDoc by id", produces = {
            MediaType.APPLICATION_JSON }, consumes = { MediaType.APPLICATION_JSON })
    void delete(@ApiPathParam(name = "id", description = "The uploadedDoc Id") @PathParam("id") String id);

    @POST
    @Path("/delete-province/")
    @ApiMethod(path = "/api/rest/uploadedDoc/delete-uploadedDoc/", verb = ApiVerb.POST, description = "delete uploadedDoc", produces = {
            MediaType.APPLICATION_JSON }, consumes = { MediaType.APPLICATION_JSON })
    void delete(@ApiBodyObject UploadedDocVo vo);

    @POST
    @Path("/findList/")
    @ApiMethod(path = "/api/rest/uploadedDoc/findList/", verb = ApiVerb.POST, description = "findList", produces = {
            MediaType.APPLICATION_JSON }, consumes = { MediaType.APPLICATION_JSON })
    List<UploadedDocVo> findList();

    @POST
    @Path("/findByReqDocId/")
    @ApiMethod(path = "/api/rest/uploadedDoc/findByReqDocId/", verb = ApiVerb.POST, description = "findByReqDocId", produces = {
            MediaType.APPLICATION_JSON }, consumes = { MediaType.APPLICATION_JSON })
    List<UploadedDocVo> findByReqDocId(
            @ApiQueryParam(name = "reqDocId", description = "The reqDoc id") @QueryParam("reqDocId") String reqDocId);
}
