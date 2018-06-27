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

import com.accentrix.hku.vo.app.OthersVo;

/** 
 * @author Jaye.Lin 
 * @date 创建时间：2018年3月6日 下午5:14:03 
 * @version 1.0 
 */

@Consumes({ MediaType.APPLICATION_JSON, MediaType.TEXT_XML })
@Produces({ MediaType.APPLICATION_JSON, MediaType.TEXT_XML })
@Api(name = "Others Service", description = "othersService")
public interface OthersService {

    @GET
    @Path("get")
    @ApiMethod(path = "/api/rest/others/get/", verb = ApiVerb.GET, description = "get others by id", produces = {
            MediaType.APPLICATION_JSON }, consumes = { MediaType.APPLICATION_JSON })
    @ApiResponseObject
    OthersVo get(@ApiQueryParam(name = "id", description = "The others id") @QueryParam("id") String id);

    @POST
    @Path("/")
    @ApiMethod(path = "/api/rest/others/", verb = ApiVerb.POST, description = "save others", produces = {
            MediaType.APPLICATION_JSON }, consumes = { MediaType.APPLICATION_JSON })
    @ApiResponseObject
    OthersVo save(@ApiBodyObject OthersVo vo);

    @POST
    @Path("/save-list/")
    @ApiMethod(path = "/api/rest/others/save-list/", verb = ApiVerb.POST, description = "save others list", produces = {
            MediaType.APPLICATION_JSON }, consumes = { MediaType.APPLICATION_JSON })
    @ApiResponseObject
    List<OthersVo> save(List<OthersVo> vos);

    @DELETE
    @Path("/{id}/")
    @ApiMethod(path = "/api/rest/others/{id}/", verb = ApiVerb.DELETE, description = "delete others by id", produces = {
            MediaType.APPLICATION_JSON }, consumes = { MediaType.APPLICATION_JSON })
    void delete(@ApiPathParam(name = "id", description = "The others Id") @PathParam("id") String id);

    @POST
    @Path("/delete-province/")
    @ApiMethod(path = "/api/rest/others/delete-others/", verb = ApiVerb.POST, description = "delete others", produces = {
            MediaType.APPLICATION_JSON }, consumes = { MediaType.APPLICATION_JSON })
    void delete(@ApiBodyObject OthersVo vo);

    @POST
    @Path("/findList/")
    @ApiMethod(path = "/api/rest/others/findList/", verb = ApiVerb.POST, description = "findList", produces = {
            MediaType.APPLICATION_JSON }, consumes = { MediaType.APPLICATION_JSON })
    List<OthersVo> findList();
}
