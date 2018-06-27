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

import com.accentrix.hku.vo.exam.SubjectVo;

/**
 * @author Jaye.Lin
 * @date 创建时间：2018年1月31日 下午2:31:20
 * @version 1.0
 */

@Consumes({ MediaType.APPLICATION_JSON, MediaType.TEXT_XML })
@Produces({ MediaType.APPLICATION_JSON, MediaType.TEXT_XML })
@Api(name = "Subject Service", description = "subjectService")
public interface SubjectService {

    @GET
    @Path("get")
    @ApiMethod(path = "/api/rest/subject/get/", verb = ApiVerb.GET, description = "get subject by id", produces = {
            MediaType.APPLICATION_JSON }, consumes = { MediaType.APPLICATION_JSON })
    @ApiResponseObject
    SubjectVo get(@ApiQueryParam(name = "id", description = "The subject id") @QueryParam("id") String id);

    @POST
    @Path("/")
    @ApiMethod(path = "/api/rest/subject/", verb = ApiVerb.POST, description = "save subject", produces = {
            MediaType.APPLICATION_JSON }, consumes = { MediaType.APPLICATION_JSON })
    @ApiResponseObject
    SubjectVo save(@ApiBodyObject SubjectVo vo);

    @POST
    @Path("/save-list/")
    @ApiMethod(path = "/api/rest/subject/save-list/", verb = ApiVerb.POST, description = "save subject list", produces = {
            MediaType.APPLICATION_JSON }, consumes = { MediaType.APPLICATION_JSON })
    @ApiResponseObject
    List<SubjectVo> save(List<SubjectVo> vos);

    @DELETE
    @Path("/{id}/")
    @ApiMethod(path = "/api/rest/subject/{id}/", verb = ApiVerb.DELETE, description = "delete subject by id", produces = {
            MediaType.APPLICATION_JSON }, consumes = { MediaType.APPLICATION_JSON })
    void delete(@ApiPathParam(name = "id", description = "The subject Id") @PathParam("id") String id);

    @POST
    @Path("/delete-province/")
    @ApiMethod(path = "/api/rest/subject/delete-subject/", verb = ApiVerb.POST, description = "delete subject", produces = {
            MediaType.APPLICATION_JSON }, consumes = { MediaType.APPLICATION_JSON })
    void delete(@ApiBodyObject SubjectVo vo);

    @POST
    @Path("/findList/")
    @ApiMethod(path = "/api/rest/subject/findList/", verb = ApiVerb.POST, description = "findList", produces = {
            MediaType.APPLICATION_JSON }, consumes = { MediaType.APPLICATION_JSON })
    List<SubjectVo> findList();

    @GET
    @Path("getBySubjectCdAndExamTypeId")
    @ApiMethod(path = "/api/rest/subject/getBySubjectCdAndExamTypeId/", verb = ApiVerb.GET, description = "get subject by subjectCd and examTypeId", produces = {
            MediaType.APPLICATION_JSON }, consumes = { MediaType.APPLICATION_JSON })
    SubjectVo getBySubjectCdAndExamTypeId(
            @ApiQueryParam(name = "examSubjectCd") @QueryParam("examSubjectCd") String examSubjectCd,
            @ApiQueryParam(name = "examTypeId") @QueryParam("examTypeId") String examTypeId);

    @GET
    @Path("getByExamTypeId")
    @ApiMethod(path = "/api/rest/subject/getByExamTypeId/", verb = ApiVerb.GET, description = "get subject by examTypeId", produces = {
            MediaType.APPLICATION_JSON }, consumes = { MediaType.APPLICATION_JSON })
    List<SubjectVo> getByExamTypeId(@ApiQueryParam(name = "examTypeId") @QueryParam("examTypeId") String examTypeId);

    //    @GET
    //    @Path("getIdsByExamTypeId")
    //    @ApiMethod(path = "/api/rest/subject/getIdsByExamTypeId/", verb = ApiVerb.GET, description = "get subject ids by examTypeId", produces = {
    //            MediaType.APPLICATION_JSON }, consumes = { MediaType.APPLICATION_JSON })
    //    List<String> getIdsByExamTypeId(@ApiQueryParam(name = "examTypeId") @QueryParam("examTypeId") String examTypeId);

    @GET
    @Path("getIdsByScoringFormulaId")
    @ApiMethod(path = "/api/rest/subject/getIdsByScoringFormulaId/", verb = ApiVerb.GET, description = "getIdsByScoringFormulaId", produces = {
            MediaType.APPLICATION_JSON }, consumes = { MediaType.APPLICATION_JSON })
    List<String> getIdsByScoringFormulaId(
            @ApiQueryParam(name = "scoringFormulaId") @QueryParam("scoringFormulaId") String scoringFormulaId,
            @ApiQueryParam(name = "type") @QueryParam("type") String type);

    @GET
    @Path("findByExamTypeIdAndExamBoardAndExamLevelAndSubjectDescNotIn")
    @ApiMethod(path = "/api/rest/subject/findByExamTypeIdAndExamBoardAndExamLevelAndSubjectDescNotIn/", verb = ApiVerb.GET, description = "findByExamTypeIdAndExamBoardAndExamLevelAndSubjectDescNotIn", produces = {
            MediaType.APPLICATION_JSON }, consumes = { MediaType.APPLICATION_JSON })
    List<SubjectVo> findByExamTypeIdAndExamBoardAndExamLevelAndSubjectDescNotIn(
            @ApiQueryParam(name = "examTypeId") @QueryParam("examTypeId") String examTypeId,
            @ApiQueryParam(name = "examBoard", description = "Exam Board") @QueryParam("examBoard") String examBoard,
            @ApiQueryParam(name = "examLevel", description = "Exam Level") @QueryParam("examLevel") String examLevel,
            @ApiQueryParam(name = "subjects") @QueryParam("subjects") List<String> subjects);

    @POST
    @Path("/findExamLevelsByExamTypeIdAndExamBoardGroupByExamLevel/")
    @ApiMethod(path = "/api/rest/subject/findExamLevelsByExamTypeIdAndExamBoardGroupByExamLevel/", verb = ApiVerb.POST, description = "findExamLevelsByExamTypeIdAndExamBoardGroupByExamLevel", produces = {
            MediaType.APPLICATION_JSON }, consumes = { MediaType.APPLICATION_JSON })
    List<String> findExamLevelsByExamTypeIdAndExamBoardGroupByExamLevel(
            @ApiQueryParam(name = "examTypeId", description = "Exam Type Id") @QueryParam("examTypeId") String examTypeId,
            @ApiQueryParam(name = "examBoard", description = "Exam Board") @QueryParam("examBoard") String examBoard);

    @POST
    @Path("/findExamBoardsByExamTypeIdAndExamLevelGroupByExamBoard/")
    @ApiMethod(path = "/api/rest/subject/findExamBoardsByExamTypeIdAndExamLevelGroupByExamBoard/", verb = ApiVerb.POST, description = "findExamBoardsByExamTypeIdAndExamLevelGroupByExamBoard", produces = {
            MediaType.APPLICATION_JSON }, consumes = { MediaType.APPLICATION_JSON })
    List<String> findExamBoardsByExamTypeIdAndExamLevelGroupByExamBoard(
            @ApiQueryParam(name = "examTypeId", description = "Exam Type Id") @QueryParam("examTypeId") String examTypeId,
            @ApiQueryParam(name = "examLevel", description = "Exam Level") @QueryParam("examLevel") String examLevel);

    @POST
    @Path("/findByExamTypeIdAndExamBoardAndExamLevel/")
    @ApiMethod(path = "/api/rest/subject/findByExamTypeIdAndExamBoardAndExamLevel/", verb = ApiVerb.POST, description = "findByExamTypeIdAndExamBoardAndExamLevel", produces = {
            MediaType.APPLICATION_JSON }, consumes = { MediaType.APPLICATION_JSON })
    List<SubjectVo> findByExamTypeIdAndExamBoardAndExamLevel(
            @ApiQueryParam(name = "examTypeId", description = "Exam Type Id") @QueryParam("examTypeId") String examTypeId,
            @ApiQueryParam(name = "examBoard", description = "Exam Board") @QueryParam("examBoard") String examBoard,
            @ApiQueryParam(name = "examLevel", description = "Exam Level") @QueryParam("examLevel") String examLevel);
}
