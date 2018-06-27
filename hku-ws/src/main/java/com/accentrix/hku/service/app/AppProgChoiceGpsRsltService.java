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

import com.accentrix.hku.vo.app.AppProgChoiceGpsRsltVo;

/**
 * @author 作者lance.mao
 * @Email lance.mao@accentrix.com
 * @date 创建时间：2018年6月1日 下午1:41:59
 */
@Consumes({ MediaType.APPLICATION_JSON, MediaType.TEXT_XML })
@Produces({ MediaType.APPLICATION_JSON, MediaType.TEXT_XML })
@Api(name = "AppProgChoiceGpsRslt Service", description = "appProgChoiceGpsRsltService")
public interface AppProgChoiceGpsRsltService {

    @GET
    @Path("get")
    @ApiMethod(path = "/api/rest/appProgChoiceGpsRslt/get/", verb = ApiVerb.GET, description = "get appProgChoiceGpsRslt by id", produces = {
            MediaType.APPLICATION_JSON }, consumes = { MediaType.APPLICATION_JSON })
    @ApiResponseObject
    AppProgChoiceGpsRsltVo get(
            @ApiQueryParam(name = "id", description = "The appProgChoiceGpsRslt id") @QueryParam("id") String id);

    @POST
    @Path("/")
    @ApiMethod(path = "/api/rest/appProgChoiceGpsRslt/", verb = ApiVerb.POST, description = "save appProgChoiceGpsRslt", produces = {
            MediaType.APPLICATION_JSON }, consumes = { MediaType.APPLICATION_JSON })
    @ApiResponseObject
    AppProgChoiceGpsRsltVo save(@ApiBodyObject AppProgChoiceGpsRsltVo vo);

    @POST
    @Path("/save-list/")
    @ApiMethod(path = "/api/rest/appProgChoiceGpsRslt/save-list/", verb = ApiVerb.POST, description = "save appProgChoiceGpsRslt list", produces = {
            MediaType.APPLICATION_JSON }, consumes = { MediaType.APPLICATION_JSON })
    @ApiResponseObject
    List<AppProgChoiceGpsRsltVo> save(List<AppProgChoiceGpsRsltVo> vos);

    @DELETE
    @Path("/{id}/")
    @ApiMethod(path = "/api/rest/appProgChoiceGpsRslt/{id}/", verb = ApiVerb.DELETE, description = "delete appProgChoiceGpsRslt by id", produces = {
            MediaType.APPLICATION_JSON }, consumes = { MediaType.APPLICATION_JSON })
    void delete(@ApiPathParam(name = "id", description = "The appProgChoiceGpsRslt Id") @PathParam("id") String id);

    @POST
    @Path("/delete-province/")
    @ApiMethod(path = "/api/rest/appProgChoiceGpsRslt/delete-appProgChoiceGpsRslt/", verb = ApiVerb.POST, description = "delete appProgChoiceGpsRslt", produces = {
            MediaType.APPLICATION_JSON }, consumes = { MediaType.APPLICATION_JSON })
    void delete(@ApiBodyObject AppProgChoiceGpsRsltVo vo);

    @POST
    @Path("/findList/")
    @ApiMethod(path = "/api/rest/appProgChoiceGpsRslt/findList/", verb = ApiVerb.POST, description = "findList", produces = {
            MediaType.APPLICATION_JSON }, consumes = { MediaType.APPLICATION_JSON })
    List<AppProgChoiceGpsRsltVo> findList();
}
