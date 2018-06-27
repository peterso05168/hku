package com.accentrix.hku.service.adm;

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

import com.accentrix.hku.vo.adm.GpsScoringSubjectVo;

/** 
 * @author Jaye.Lin 
 * @date 创建时间：2018年3月19日 上午11:43:29 
 * @version 1.0 
 */

@Consumes({ MediaType.APPLICATION_JSON, MediaType.TEXT_XML })
@Produces({ MediaType.APPLICATION_JSON, MediaType.TEXT_XML })
@Api(name = "GpsScoringSubject Service", description = "gpsScoringSubjectService")
public interface GpsScoringSubjectService {

    @GET
    @Path("get")
    @ApiMethod(path = "/api/rest/gpsScoringSubject/get/", verb = ApiVerb.GET, description = "get gpsScoringSubject by id", produces = {
            MediaType.APPLICATION_JSON }, consumes = { MediaType.APPLICATION_JSON })
    @ApiResponseObject
    GpsScoringSubjectVo get(
            @ApiQueryParam(name = "id", description = "The gpsScoringSubject id") @QueryParam("id") String id);

    @POST
    @Path("/")
    @ApiMethod(path = "/api/rest/gpsScoringSubject/", verb = ApiVerb.POST, description = "save gpsScoringSubject", produces = {
            MediaType.APPLICATION_JSON }, consumes = { MediaType.APPLICATION_JSON })
    @ApiResponseObject
    GpsScoringSubjectVo save(@ApiBodyObject GpsScoringSubjectVo vo);

    @POST
    @Path("/save-list/")
    @ApiMethod(path = "/api/rest/gpsScoringSubject/save-list/", verb = ApiVerb.POST, description = "save gpsScoringSubject list", produces = {
            MediaType.APPLICATION_JSON }, consumes = { MediaType.APPLICATION_JSON })
    @ApiResponseObject
    List<GpsScoringSubjectVo> save(List<GpsScoringSubjectVo> vos);

    @DELETE
    @Path("/{id}/")
    @ApiMethod(path = "/api/rest/gpsScoringSubject/{id}/", verb = ApiVerb.DELETE, description = "delete gpsScoringSubject by id", produces = {
            MediaType.APPLICATION_JSON }, consumes = { MediaType.APPLICATION_JSON })
    void delete(@ApiPathParam(name = "id", description = "The gpsScoringSubject Id") @PathParam("id") String id);

    @POST
    @Path("/delete-province/")
    @ApiMethod(path = "/api/rest/gpsScoringSubject/delete-gpsScoringSubject/", verb = ApiVerb.POST, description = "delete gpsScoringSubject", produces = {
            MediaType.APPLICATION_JSON }, consumes = { MediaType.APPLICATION_JSON })
    void delete(@ApiBodyObject GpsScoringSubjectVo vo);

    @POST
    @Path("/findList/")
    @ApiMethod(path = "/api/rest/gpsScoringSubject/findList/", verb = ApiVerb.POST, description = "findList", produces = {
            MediaType.APPLICATION_JSON }, consumes = { MediaType.APPLICATION_JSON })
    List<GpsScoringSubjectVo> findList();

    @GET
    @Path("findByScoringFormulaId")
    @ApiMethod(path = "/api/rest/subject/findByScoringFormulaId/", verb = ApiVerb.GET, description = "findByScoringFormulaId", produces = {
            MediaType.APPLICATION_JSON }, consumes = { MediaType.APPLICATION_JSON })
    List<GpsScoringSubjectVo> findByScoringFormulaId(
            @ApiQueryParam(name = "scoringFormulaId") @QueryParam("scoringFormulaId") String scoringFormulaId,
            @ApiQueryParam(name = "type") @QueryParam("type") String type);
}
