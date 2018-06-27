package com.accentrix.hku.service.email;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

import org.jsondoc.core.annotation.Api;
import org.jsondoc.core.annotation.ApiBodyObject;
import org.jsondoc.core.annotation.ApiMethod;
import org.jsondoc.core.annotation.ApiQueryParam;
import org.jsondoc.core.pojo.ApiVerb;

import com.accentrix.hku.vo.email.MailInfo;

/**
 * @author Jaye.Lin
 * @date 创建时间：2018年3月28日 上午10:11:42
 * @version 1.0
 */

@Consumes({ MediaType.APPLICATION_JSON, MediaType.TEXT_XML })
@Produces({ MediaType.APPLICATION_JSON, MediaType.TEXT_XML })
@Api(name = "MailSender Service", description = "mailSenderService")
public interface MailSenderService {

    @POST
    @Path("/sendTextMail/")
    @ApiMethod(path = "/api/rest/mailSender/sendTextMail/", verb = ApiVerb.POST, description = "sendTextMail", produces = {
            MediaType.APPLICATION_JSON }, consumes = { MediaType.APPLICATION_JSON })
    public boolean sendTextMail(@ApiBodyObject MailInfo mailInfo,
            @ApiQueryParam(name = "mailType") @QueryParam("mailType") int mailType);

    @POST
    @Path("/sendHtmlMail/")
    @ApiMethod(path = "/api/rest/mailSender/sendHtmlMail/", verb = ApiVerb.POST, description = "sendHtmlMail", produces = {
            MediaType.APPLICATION_JSON }, consumes = { MediaType.APPLICATION_JSON })
    public boolean sendHtmlMail(@ApiBodyObject MailInfo mailInfo,
            @ApiQueryParam(name = "mailType") @QueryParam("mailType") int mailType);
}
