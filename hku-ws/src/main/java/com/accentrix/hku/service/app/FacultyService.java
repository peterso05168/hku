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

import com.accentrix.hku.vo.app.FacultyVo;

/** 
 * @author Jaye.Lin 
 * @date 创建时间：2018年1月31日 下午2:21:14 
 * @version 1.0 
 */

@Consumes({ MediaType.APPLICATION_JSON, MediaType.TEXT_XML })
@Produces({ MediaType.APPLICATION_JSON, MediaType.TEXT_XML })
@Api(name = "Faculty Service", description = "facultyService")
public interface FacultyService {

    @GET
    @Path("get")
    @ApiMethod(path = "/api/rest/faculty/get/", verb = ApiVerb.GET, description = "get faculty by id", produces = {
            MediaType.APPLICATION_JSON }, consumes = { MediaType.APPLICATION_JSON })
    @ApiResponseObject
    FacultyVo get(@ApiQueryParam(name = "id", description = "The faculty id") @QueryParam("id") String id);

    @POST
    @Path("/")
    @ApiMethod(path = "/api/rest/faculty/", verb = ApiVerb.POST, description = "save faculty", produces = {
            MediaType.APPLICATION_JSON }, consumes = { MediaType.APPLICATION_JSON })
    @ApiResponseObject
    FacultyVo save(@ApiBodyObject FacultyVo vo);

    @POST
    @Path("/save-list/")
    @ApiMethod(path = "/api/rest/faculty/save-list/", verb = ApiVerb.POST, description = "save faculty list", produces = {
            MediaType.APPLICATION_JSON }, consumes = { MediaType.APPLICATION_JSON })
    @ApiResponseObject
    List<FacultyVo> save(List<FacultyVo> vos);

    @DELETE
    @Path("/{id}/")
    @ApiMethod(path = "/api/rest/faculty/{id}/", verb = ApiVerb.DELETE, description = "delete faculty by id", produces = {
            MediaType.APPLICATION_JSON }, consumes = { MediaType.APPLICATION_JSON })
    void delete(@ApiPathParam(name = "id", description = "The faculty Id") @PathParam("id") String id);

    @POST
    @Path("/delete-province/")
    @ApiMethod(path = "/api/rest/faculty/delete-faculty/", verb = ApiVerb.POST, description = "delete faculty", produces = {
            MediaType.APPLICATION_JSON }, consumes = { MediaType.APPLICATION_JSON })
    void delete(@ApiBodyObject FacultyVo vo);

    @POST
    @Path("/findList/")
    @ApiMethod(path = "/api/rest/faculty/findList/", verb = ApiVerb.POST, description = "findList", produces = {
            MediaType.APPLICATION_JSON }, consumes = { MediaType.APPLICATION_JSON })
    List<FacultyVo> findList();
}
