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

import com.accentrix.hku.vo.app.BestExamSubjRsltVo;
import com.accentrix.hku.vo.app.QualificationRsltVo;

/**
 * @author Jaye.Lin
 * @date 创建时间：2018年1月31日 下午2:25:09
 * @version 1.0
 */

@Consumes({ MediaType.APPLICATION_JSON, MediaType.TEXT_XML })
@Produces({ MediaType.APPLICATION_JSON, MediaType.TEXT_XML })
@Api(name = "Qualification Rslt Service", description = "qualificationRsltService")
public interface QualificationRsltService {

    @GET
    @Path("get")
    @ApiMethod(path = "/api/rest/qualificationRslt/get/", verb = ApiVerb.GET, description = "get qualificationRslt by id", produces = {
            MediaType.APPLICATION_JSON }, consumes = { MediaType.APPLICATION_JSON })
    @ApiResponseObject
    QualificationRsltVo get(
            @ApiQueryParam(name = "id", description = "The qualificationRslt id") @QueryParam("id") String id);

    @POST
    @Path("/")
    @ApiMethod(path = "/api/rest/qualificationRslt/", verb = ApiVerb.POST, description = "save qualificationRslt", produces = {
            MediaType.APPLICATION_JSON }, consumes = { MediaType.APPLICATION_JSON })
    @ApiResponseObject
    QualificationRsltVo save(@ApiBodyObject QualificationRsltVo vo);

    @POST
    @Path("/save-list/")
    @ApiMethod(path = "/api/rest/qualificationRslt/save-list/", verb = ApiVerb.POST, description = "save qualificationRslt list", produces = {
            MediaType.APPLICATION_JSON }, consumes = { MediaType.APPLICATION_JSON })
    @ApiResponseObject
    List<QualificationRsltVo> save(List<QualificationRsltVo> vos);

    @DELETE
    @Path("/{id}/")
    @ApiMethod(path = "/api/rest/qualificationRslt/{id}/", verb = ApiVerb.DELETE, description = "delete qualificationRslt by id", produces = {
            MediaType.APPLICATION_JSON }, consumes = { MediaType.APPLICATION_JSON })
    void delete(@ApiPathParam(name = "id", description = "The qualificationRslt Id") @PathParam("id") String id);

    @POST
    @Path("/delete-province/")
    @ApiMethod(path = "/api/rest/qualificationRslt/delete-qualificationRslt/", verb = ApiVerb.POST, description = "delete qualificationRslt", produces = {
            MediaType.APPLICATION_JSON }, consumes = { MediaType.APPLICATION_JSON })
    void delete(@ApiBodyObject QualificationRsltVo vo);

    @POST
    @Path("/findList/")
    @ApiMethod(path = "/api/rest/qualificationRslt/findList/", verb = ApiVerb.POST, description = "findList", produces = {
            MediaType.APPLICATION_JSON }, consumes = { MediaType.APPLICATION_JSON })
    List<QualificationRsltVo> findList();

    @POST
    @Path("/getByAppQualificationId/")
    @ApiMethod(path = "/api/rest/qualificationRslt/getByAppQualificationId/", verb = ApiVerb.POST, description = "getByAppQualificationId", produces = {
            MediaType.APPLICATION_JSON }, consumes = { MediaType.APPLICATION_JSON })
    List<QualificationRsltVo> getByAppQualificationId(
            @ApiQueryParam(name = "appQualificationId") @QueryParam("appQualificationId") String appQualificationId);

    @POST
    @Path("/findByQualificationIdInAndSubjectId/")
    @ApiMethod(path = "/api/rest/qualificationRslt/findByQualificationIdInAndSubjectId/", verb = ApiVerb.POST, description = "findByQualificationIdInAndSubjectId", produces = {
            MediaType.APPLICATION_JSON }, consumes = { MediaType.APPLICATION_JSON })
    List<QualificationRsltVo> findByQualificationIdInAndSubjectId(
            @ApiQueryParam(name = "qualificationIds", description = "The qualification ids") @QueryParam("qualificationIds") List<String> qualificationIds,
            @ApiQueryParam(name = "subjectId", description = "The subject id") @QueryParam("subjectId") String subjectId);

    @POST
    @Path("/getExamSubjectIdsByApplicationIdAndExamTypeId/")
    @ApiMethod(path = "/api/rest/qualificationRslt/getExamSubjectIdsByApplicationIdAndExamTypeId/", verb = ApiVerb.POST, description = "getExamSubjectIdsByApplicationIdAndExamTypeId", produces = {
            MediaType.APPLICATION_JSON }, consumes = { MediaType.APPLICATION_JSON })
    List<String> getExamSubjectIdsByApplicationIdAndExamTypeId(
            @ApiQueryParam(name = "applicationId", description = "The application id") @QueryParam("applicationId") String applicationId,
            @ApiQueryParam(name = "examTypeId", description = "The examType id") @QueryParam("examTypeId") String examTypeId);

    @POST
    @Path("/getBestExamSubjRsltByApplicationIdAndExamTypeIdAndExamSubjectId/")
    @ApiMethod(path = "/api/rest/qualificationRslt/getBestExamSubjRsltByApplicationIdAndExamTypeIdAndExamSubjectId/", verb = ApiVerb.POST, description = "getBestExamSubjRsltByApplicationIdAndExamTypeIdAndExamSubjectId", produces = {
            MediaType.APPLICATION_JSON }, consumes = { MediaType.APPLICATION_JSON })
    BestExamSubjRsltVo getBestExamSubjRsltByApplicationIdAndExamTypeIdAndExamSubjectId(
            @ApiQueryParam(name = "applicationId", description = "The application id") @QueryParam("applicationId") String applicationId,
            @ApiQueryParam(name = "examTypeId", description = "The examType id") @QueryParam("examTypeId") String examTypeId,
            @ApiQueryParam(name = "subjectId", description = "The subject id") @QueryParam("subjectId") String subjectId);

    @POST
    @Path("/getBestGceSubjsPredictedAndActualScores/")
    @ApiMethod(path = "/api/rest/qualificationRslt/getBestGceSubjsPredictedAndActualScores/", verb = ApiVerb.POST, description = "getBestGceSubjsPredictedAndActualScores", produces = {
            MediaType.APPLICATION_JSON }, consumes = { MediaType.APPLICATION_JSON })
    List<BestExamSubjRsltVo> getBestGceSubjsPredictedAndActualScores(
            @ApiQueryParam(name = "applicationId", description = "The application id") @QueryParam("applicationId") String applicationId,
            @ApiQueryParam(name = "examTypeId", description = "The examType id") @QueryParam("examTypeId") String examTypeId,
            @ApiQueryParam(name = "subjectId", description = "The subject id") @QueryParam("subjectId") String subjectId);
}
