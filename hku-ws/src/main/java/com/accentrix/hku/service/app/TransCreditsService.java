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

import com.accentrix.hku.vo.app.TransCreditsVo;

/**
 * @author Jaye.Lin
 * @date 创建时间：2018年2月9日 下午3:13:34
 * @version 1.0
 */

@Consumes({ MediaType.APPLICATION_JSON, MediaType.TEXT_XML })
@Produces({ MediaType.APPLICATION_JSON, MediaType.TEXT_XML })
@Api(name = "TransCredits Service", description = "transCreditsService")
public interface TransCreditsService {

    @GET
    @Path("get")
    @ApiMethod(path = "/api/rest/transCredits/get/", verb = ApiVerb.GET, description = "get transCredits by id", produces = {
            MediaType.APPLICATION_JSON }, consumes = { MediaType.APPLICATION_JSON })
    @ApiResponseObject
    TransCreditsVo get(@ApiQueryParam(name = "id", description = "The transCredits id") @QueryParam("id") String id);

    @POST
    @Path("/")
    @ApiMethod(path = "/api/rest/transCredits/", verb = ApiVerb.POST, description = "save transCredits", produces = {
            MediaType.APPLICATION_JSON }, consumes = { MediaType.APPLICATION_JSON })
    @ApiResponseObject
    TransCreditsVo save(@ApiBodyObject TransCreditsVo vo);

    @POST
    @Path("/save-list/")
    @ApiMethod(path = "/api/rest/transCredits/save-list/", verb = ApiVerb.POST, description = "save transCredits list", produces = {
            MediaType.APPLICATION_JSON }, consumes = { MediaType.APPLICATION_JSON })
    @ApiResponseObject
    List<TransCreditsVo> save(List<TransCreditsVo> vos);

    @DELETE
    @Path("/{id}/")
    @ApiMethod(path = "/api/rest/transCredits/{id}/", verb = ApiVerb.DELETE, description = "delete transCredits by id", produces = {
            MediaType.APPLICATION_JSON }, consumes = { MediaType.APPLICATION_JSON })
    void delete(@ApiPathParam(name = "id", description = "The transCredits Id") @PathParam("id") String id);

    @POST
    @Path("/delete-province/")
    @ApiMethod(path = "/api/rest/transCredits/delete-transCredits/", verb = ApiVerb.POST, description = "delete transCredits", produces = {
            MediaType.APPLICATION_JSON }, consumes = { MediaType.APPLICATION_JSON })
    void delete(@ApiBodyObject TransCreditsVo vo);

    @POST
    @Path("/findList/")
    @ApiMethod(path = "/api/rest/transCredits/findList/", verb = ApiVerb.POST, description = "findList", produces = {
            MediaType.APPLICATION_JSON }, consumes = { MediaType.APPLICATION_JSON })
    List<TransCreditsVo> findList();

    @GET
    @Path("/getByApplicationId/")
    @ApiMethod(path = "/api/rest/transCredits/getByApplicationId/", verb = ApiVerb.GET, description = "get transCredits by applicationId", produces = {
            MediaType.APPLICATION_JSON }, consumes = { MediaType.APPLICATION_JSON })
    TransCreditsVo getByApplicationId(
            @ApiQueryParam(name = "applicationId") @QueryParam("applicationId") String applicationId);
}
