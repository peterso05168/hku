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

import com.accentrix.hku.vo.app.HkuProgrammeVo;

/** 
 * @author Jaye.Lin 
 * @date 创建时间：2018年1月31日 下午2:21:35 
 * @version 1.0 
 */

@Consumes({ MediaType.APPLICATION_JSON, MediaType.TEXT_XML })
@Produces({ MediaType.APPLICATION_JSON, MediaType.TEXT_XML })
@Api(name = "HkuProgramme Service", description = "hkuProgrammeService")
public interface HkuProgrammeService {

    @GET
    @Path("get")
    @ApiMethod(path = "/api/rest/hkuProgramme/get/", verb = ApiVerb.GET, description = "get hkuProgramme by id", produces = {
            MediaType.APPLICATION_JSON }, consumes = { MediaType.APPLICATION_JSON })
    @ApiResponseObject
    HkuProgrammeVo get(@ApiQueryParam(name = "id", description = "The hkuProgramme id") @QueryParam("id") String id);

    @POST
    @Path("/")
    @ApiMethod(path = "/api/rest/hkuProgramme/", verb = ApiVerb.POST, description = "save hkuProgramme", produces = {
            MediaType.APPLICATION_JSON }, consumes = { MediaType.APPLICATION_JSON })
    @ApiResponseObject
    HkuProgrammeVo save(@ApiBodyObject HkuProgrammeVo vo);

    @POST
    @Path("/save-list/")
    @ApiMethod(path = "/api/rest/hkuProgramme/save-list/", verb = ApiVerb.POST, description = "save hkuProgramme list", produces = {
            MediaType.APPLICATION_JSON }, consumes = { MediaType.APPLICATION_JSON })
    @ApiResponseObject
    List<HkuProgrammeVo> save(List<HkuProgrammeVo> vos);

    @DELETE
    @Path("/{id}/")
    @ApiMethod(path = "/api/rest/hkuProgramme/{id}/", verb = ApiVerb.DELETE, description = "delete hkuProgramme by id", produces = {
            MediaType.APPLICATION_JSON }, consumes = { MediaType.APPLICATION_JSON })
    void delete(@ApiPathParam(name = "id", description = "The hkuProgramme Id") @PathParam("id") String id);

    @POST
    @Path("/delete-province/")
    @ApiMethod(path = "/api/rest/hkuProgramme/delete-hkuProgramme/", verb = ApiVerb.POST, description = "delete hkuProgramme", produces = {
            MediaType.APPLICATION_JSON }, consumes = { MediaType.APPLICATION_JSON })
    void delete(@ApiBodyObject HkuProgrammeVo vo);

    @POST
    @Path("/findList/")
    @ApiMethod(path = "/api/rest/hkuProgramme/findList/", verb = ApiVerb.POST, description = "findList", produces = {
            MediaType.APPLICATION_JSON }, consumes = { MediaType.APPLICATION_JSON })
    List<HkuProgrammeVo> findList();

    @POST
    @Path("/findVos/")
    @ApiMethod(path = "/api/rest/hkuProgramme/findVos/", verb = ApiVerb.POST, description = "findVos", produces = {
            MediaType.APPLICATION_JSON }, consumes = { MediaType.APPLICATION_JSON })
    List<HkuProgrammeVo> findVos(@ApiQueryParam(name = "staffId") @QueryParam("staffId") String staffId);

    @POST
    @Path("/findFacultyCd/")
    @ApiMethod(path = "/api/rest/hkuProgramme/findFacultyCd/", verb = ApiVerb.POST, description = "findFacultyCd", produces = {
            MediaType.APPLICATION_JSON }, consumes = { MediaType.APPLICATION_JSON })
    List<String> findFacultyCd();

    @GET
    @Path("/findByFacultyCd/")
    @ApiMethod(path = "/api/rest/hkuProgramme/findByFacultyCd/", verb = ApiVerb.GET, description = "findByFacultyCd", produces = {
            MediaType.APPLICATION_JSON }, consumes = { MediaType.APPLICATION_JSON })
    List<HkuProgrammeVo> findByFacultyCd(@ApiQueryParam(name = "facultyCd") @QueryParam("facultyCd") String facultyCd);
}
