package com.accentrix.hku.service.applicant;

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

import com.accentrix.hku.vo.applicant.MessageVo;

/** 
 * @author Jaye.Lin 
 * @date 创建时间：2018年1月31日 下午2:30:28 
 * @version 1.0 
 */

@Consumes({ MediaType.APPLICATION_JSON, MediaType.TEXT_XML })
@Produces({ MediaType.APPLICATION_JSON, MediaType.TEXT_XML })
@Api(name = "Message Service", description = "messageService")
public interface MessageService {

    @GET
    @Path("get")
    @ApiMethod(path = "/api/rest/message/get/", verb = ApiVerb.GET, description = "get message by id", produces = {
            MediaType.APPLICATION_JSON }, consumes = { MediaType.APPLICATION_JSON })
    @ApiResponseObject
    MessageVo get(@ApiQueryParam(name = "id", description = "The message id") @QueryParam("id") String id);

    @POST
    @Path("/")
    @ApiMethod(path = "/api/rest/message/", verb = ApiVerb.POST, description = "save message", produces = {
            MediaType.APPLICATION_JSON }, consumes = { MediaType.APPLICATION_JSON })
    @ApiResponseObject
    MessageVo save(@ApiBodyObject MessageVo vo);

    @POST
    @Path("/save-list/")
    @ApiMethod(path = "/api/rest/message/save-list/", verb = ApiVerb.POST, description = "save message list", produces = {
            MediaType.APPLICATION_JSON }, consumes = { MediaType.APPLICATION_JSON })
    @ApiResponseObject
    List<MessageVo> save(List<MessageVo> vos);

    @DELETE
    @Path("/{id}/")
    @ApiMethod(path = "/api/rest/message/{id}/", verb = ApiVerb.DELETE, description = "delete message by id", produces = {
            MediaType.APPLICATION_JSON }, consumes = { MediaType.APPLICATION_JSON })
    void delete(@ApiPathParam(name = "id", description = "The message Id") @PathParam("id") String id);

    @POST
    @Path("/delete-province/")
    @ApiMethod(path = "/api/rest/message/delete-message/", verb = ApiVerb.POST, description = "delete message", produces = {
            MediaType.APPLICATION_JSON }, consumes = { MediaType.APPLICATION_JSON })
    void delete(@ApiBodyObject MessageVo vo);

    @POST
    @Path("/findList/")
    @ApiMethod(path = "/api/rest/message/findList/", verb = ApiVerb.POST, description = "findList", produces = {
            MediaType.APPLICATION_JSON }, consumes = { MediaType.APPLICATION_JSON })
    List<MessageVo> findList();
}
