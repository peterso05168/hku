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

import com.accentrix.hku.vo.app.CounselorVo;

/** 
 * @author Jaye.Lin 
 * @date 创建时间：2018年1月31日 下午2:20:30 
 * @version 1.0 
 */

@Consumes({ MediaType.APPLICATION_JSON, MediaType.TEXT_XML })
@Produces({ MediaType.APPLICATION_JSON, MediaType.TEXT_XML })
@Api(name = "Counselor Service", description = "counselorService")
public interface CounselorService {

    @GET
    @Path("get")
    @ApiMethod(path = "/api/rest/counselor/get/", verb = ApiVerb.GET, description = "get counselor by id", produces = {
            MediaType.APPLICATION_JSON }, consumes = { MediaType.APPLICATION_JSON })
    @ApiResponseObject
    CounselorVo get(@ApiQueryParam(name = "id", description = "The counselor id") @QueryParam("id") String id);

    @POST
    @Path("/")
    @ApiMethod(path = "/api/rest/counselor/", verb = ApiVerb.POST, description = "save counselor", produces = {
            MediaType.APPLICATION_JSON }, consumes = { MediaType.APPLICATION_JSON })
    @ApiResponseObject
    CounselorVo save(@ApiBodyObject CounselorVo vo);

    @POST
    @Path("/save-list/")
    @ApiMethod(path = "/api/rest/counselor/save-list/", verb = ApiVerb.POST, description = "save counselor list", produces = {
            MediaType.APPLICATION_JSON }, consumes = { MediaType.APPLICATION_JSON })
    @ApiResponseObject
    List<CounselorVo> save(List<CounselorVo> vos);

    @DELETE
    @Path("/{id}/")
    @ApiMethod(path = "/api/rest/counselor/{id}/", verb = ApiVerb.DELETE, description = "delete counselor by id", produces = {
            MediaType.APPLICATION_JSON }, consumes = { MediaType.APPLICATION_JSON })
    void delete(@ApiPathParam(name = "id", description = "The counselor Id") @PathParam("id") String id);

    @POST
    @Path("/delete-province/")
    @ApiMethod(path = "/api/rest/counselor/delete-counselor/", verb = ApiVerb.POST, description = "delete counselor", produces = {
            MediaType.APPLICATION_JSON }, consumes = { MediaType.APPLICATION_JSON })
    void delete(@ApiBodyObject CounselorVo vo);

    @POST
    @Path("/findList/")
    @ApiMethod(path = "/api/rest/counselor/findList/", verb = ApiVerb.POST, description = "findList", produces = {
            MediaType.APPLICATION_JSON }, consumes = { MediaType.APPLICATION_JSON })
    List<CounselorVo> findList();

    @POST
    @Path("/findByFullName/")
    @ApiMethod(path = "/api/rest/counselor/findByFullName/", verb = ApiVerb.POST, description = "findByFullName", produces = {
            MediaType.APPLICATION_JSON }, consumes = { MediaType.APPLICATION_JSON })
    @ApiResponseObject
    CounselorVo findByFullName(@ApiQueryParam(name = "fullName") @QueryParam("fullName") String fullName);
}
