package com.accentrix.hku.service.exam;

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

import com.accentrix.hku.vo.exam.GradeVo;

/**
 * @author Jaye.Lin
 * @date 创建时间：2018年1月31日 下午2:30:54
 * @version 1.0
 */

@Consumes({ MediaType.APPLICATION_JSON, MediaType.TEXT_XML })
@Produces({ MediaType.APPLICATION_JSON, MediaType.TEXT_XML })
@Api(name = "Grade Service", description = "gradeService")
public interface GradeService {

    @GET
    @Path("get")
    @ApiMethod(path = "/api/rest/grade/get/", verb = ApiVerb.GET, description = "get grade by id", produces = {
            MediaType.APPLICATION_JSON }, consumes = { MediaType.APPLICATION_JSON })
    @ApiResponseObject
    GradeVo get(@ApiQueryParam(name = "id", description = "The grade id") @QueryParam("id") String id);

    @POST
    @Path("/")
    @ApiMethod(path = "/api/rest/grade/", verb = ApiVerb.POST, description = "save grade", produces = {
            MediaType.APPLICATION_JSON }, consumes = { MediaType.APPLICATION_JSON })
    @ApiResponseObject
    GradeVo save(@ApiBodyObject GradeVo vo);

    @POST
    @Path("/save-list/")
    @ApiMethod(path = "/api/rest/grade/save-list/", verb = ApiVerb.POST, description = "save grade list", produces = {
            MediaType.APPLICATION_JSON }, consumes = { MediaType.APPLICATION_JSON })
    @ApiResponseObject
    List<GradeVo> save(List<GradeVo> vos);

    @DELETE
    @Path("/{id}/")
    @ApiMethod(path = "/api/rest/grade/{id}/", verb = ApiVerb.DELETE, description = "delete grade by id", produces = {
            MediaType.APPLICATION_JSON }, consumes = { MediaType.APPLICATION_JSON })
    void delete(@ApiPathParam(name = "id", description = "The grade Id") @PathParam("id") String id);

    @POST
    @Path("/delete-province/")
    @ApiMethod(path = "/api/rest/grade/delete-grade/", verb = ApiVerb.POST, description = "delete grade", produces = {
            MediaType.APPLICATION_JSON }, consumes = { MediaType.APPLICATION_JSON })
    void delete(@ApiBodyObject GradeVo vo);

    @POST
    @Path("/findList/")
    @ApiMethod(path = "/api/rest/grade/findList/", verb = ApiVerb.POST, description = "findList", produces = {
            MediaType.APPLICATION_JSON }, consumes = { MediaType.APPLICATION_JSON })
    List<GradeVo> findList();

    @POST
    @Path("/getByExamTypeId/")
    @ApiMethod(path = "/api/rest/grade/getByExamTypeId/", verb = ApiVerb.POST, description = "getByExamTypeId", produces = {
            MediaType.APPLICATION_JSON }, consumes = { MediaType.APPLICATION_JSON })
    List<GradeVo> getByExamTypeId(@ApiQueryParam(name = "examTypeId") @QueryParam("examTypeId") String examTypeId);

    @POST
    @Path("/getByExamTypeIdAndGradeCd/")
    @ApiMethod(path = "/api/rest/grade/getByExamTypeIdAndGradeCd/", verb = ApiVerb.POST, description = "getByExamTypeIdAndGradeCd", produces = {
            MediaType.APPLICATION_JSON }, consumes = { MediaType.APPLICATION_JSON })
    GradeVo getByExamTypeIdAndGradeCd(@ApiQueryParam(name = "examTypeId") @QueryParam("examTypeId") String examTypeId,
            @ApiQueryParam(name = "gradeCd") @QueryParam("gradeCd") String gradeCd);

    @POST
    @Path("/getByExamTypeIdAndExamLevel/")
    @ApiMethod(path = "/api/rest/grade/getByExamTypeIdAndExamLevel/", verb = ApiVerb.POST, description = "getByExamTypeIdAndExamLevel", produces = {
            MediaType.APPLICATION_JSON }, consumes = { MediaType.APPLICATION_JSON })
    List<GradeVo> getByExamTypeIdAndExamLevel(
            @ApiQueryParam(name = "examTypeId") @QueryParam("examTypeId") String examTypeId,
            @ApiQueryParam(name = "examLevel") @QueryParam("examLevel") String examLevel);

    @POST
    @Path("/getByExamTypeIdAndGradeCdAndExamLevel/")
    @ApiMethod(path = "/api/rest/grade/getByExamTypeIdAndGradeCdAndExamLevel/", verb = ApiVerb.POST, description = "getByExamTypeIdAndGradeCdAndExamLevel", produces = {
            MediaType.APPLICATION_JSON }, consumes = { MediaType.APPLICATION_JSON })
    GradeVo getByExamTypeIdAndGradeCdAndExamLevel(
            @ApiQueryParam(name = "examTypeId") @QueryParam("examTypeId") String examTypeId,
            @ApiQueryParam(name = "gradeCd") @QueryParam("gradeCd") String gradeCd,
            @ApiQueryParam(name = "examLevel") @QueryParam("examLevel") String examLevel);
}
