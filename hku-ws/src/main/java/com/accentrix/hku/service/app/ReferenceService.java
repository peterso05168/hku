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

import com.accentrix.hku.vo.app.ReferenceVo;

/**
 * @author Jaye.Lin
 * @date 创建时间：2018年1月31日 下午2:25:54
 * @version 1.0
 */

@Consumes({ MediaType.APPLICATION_JSON, MediaType.TEXT_XML })
@Produces({ MediaType.APPLICATION_JSON, MediaType.TEXT_XML })
@Api(name = "Reference Service", description = "referenceService")
public interface ReferenceService {

    @GET
    @Path("get")
    @ApiMethod(path = "/api/rest/reference/get/", verb = ApiVerb.GET, description = "get reference by id", produces = {
            MediaType.APPLICATION_JSON }, consumes = { MediaType.APPLICATION_JSON })
    @ApiResponseObject
    ReferenceVo get(@ApiQueryParam(name = "id", description = "The reference id") @QueryParam("id") String id);

    @POST
    @Path("/")
    @ApiMethod(path = "/api/rest/reference/", verb = ApiVerb.POST, description = "save reference", produces = {
            MediaType.APPLICATION_JSON }, consumes = { MediaType.APPLICATION_JSON })
    @ApiResponseObject
    ReferenceVo save(@ApiBodyObject ReferenceVo vo);

    @POST
    @Path("/save-list/")
    @ApiMethod(path = "/api/rest/reference/save-list/", verb = ApiVerb.POST, description = "save reference list", produces = {
            MediaType.APPLICATION_JSON }, consumes = { MediaType.APPLICATION_JSON })
    @ApiResponseObject
    List<ReferenceVo> save(List<ReferenceVo> vos);

    @DELETE
    @Path("/{id}/")
    @ApiMethod(path = "/api/rest/reference/{id}/", verb = ApiVerb.DELETE, description = "delete reference by id", produces = {
            MediaType.APPLICATION_JSON }, consumes = { MediaType.APPLICATION_JSON })
    void delete(@ApiPathParam(name = "id", description = "The reference Id") @PathParam("id") String id);

    @POST
    @Path("/delete-province/")
    @ApiMethod(path = "/api/rest/reference/delete-reference/", verb = ApiVerb.POST, description = "delete reference", produces = {
            MediaType.APPLICATION_JSON }, consumes = { MediaType.APPLICATION_JSON })
    void delete(@ApiBodyObject ReferenceVo vo);

    @POST
    @Path("/findList/")
    @ApiMethod(path = "/api/rest/reference/findList/", verb = ApiVerb.POST, description = "findList", produces = {
            MediaType.APPLICATION_JSON }, consumes = { MediaType.APPLICATION_JSON })
    List<ReferenceVo> findList();

}
