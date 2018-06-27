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

import com.accentrix.hku.vo.adm.ScoringFormulaVo;

/** 
 * @author Jaye.Lin 
 * @date 创建时间：2018年3月19日 上午11:43:51 
 * @version 1.0 
 */

@Consumes({ MediaType.APPLICATION_JSON, MediaType.TEXT_XML })
@Produces({ MediaType.APPLICATION_JSON, MediaType.TEXT_XML })
@Api(name = "ScoringFormula Service", description = "scoringFormulaService")
public interface ScoringFormulaService {

    @GET
    @Path("get")
    @ApiMethod(path = "/api/rest/scoringFormula/get/", verb = ApiVerb.GET, description = "get scoringFormula by id", produces = {
            MediaType.APPLICATION_JSON }, consumes = { MediaType.APPLICATION_JSON })
    @ApiResponseObject
    ScoringFormulaVo get(
            @ApiQueryParam(name = "id", description = "The scoringFormula id") @QueryParam("id") String id);

    @POST
    @Path("/")
    @ApiMethod(path = "/api/rest/scoringFormula/", verb = ApiVerb.POST, description = "save scoringFormula", produces = {
            MediaType.APPLICATION_JSON }, consumes = { MediaType.APPLICATION_JSON })
    @ApiResponseObject
    ScoringFormulaVo save(@ApiBodyObject ScoringFormulaVo vo);

    @POST
    @Path("/save-list/")
    @ApiMethod(path = "/api/rest/scoringFormula/save-list/", verb = ApiVerb.POST, description = "save scoringFormula list", produces = {
            MediaType.APPLICATION_JSON }, consumes = { MediaType.APPLICATION_JSON })
    @ApiResponseObject
    List<ScoringFormulaVo> save(List<ScoringFormulaVo> vos);

    @DELETE
    @Path("/{id}/")
    @ApiMethod(path = "/api/rest/scoringFormula/{id}/", verb = ApiVerb.DELETE, description = "delete scoringFormula by id", produces = {
            MediaType.APPLICATION_JSON }, consumes = { MediaType.APPLICATION_JSON })
    void delete(@ApiPathParam(name = "id", description = "The scoringFormula Id") @PathParam("id") String id);

    @POST
    @Path("/delete-province/")
    @ApiMethod(path = "/api/rest/scoringFormula/delete-scoringFormula/", verb = ApiVerb.POST, description = "delete scoringFormula", produces = {
            MediaType.APPLICATION_JSON }, consumes = { MediaType.APPLICATION_JSON })
    void delete(@ApiBodyObject ScoringFormulaVo vo);

    @POST
    @Path("/findList/")
    @ApiMethod(path = "/api/rest/scoringFormula/findList/", verb = ApiVerb.POST, description = "findList", produces = {
            MediaType.APPLICATION_JSON }, consumes = { MediaType.APPLICATION_JSON })
    List<ScoringFormulaVo> findList();

    @POST
    @Path("/findByFormProgId/")
    @ApiMethod(path = "/api/rest/scoringFormula/findByFormProgId/", verb = ApiVerb.POST, description = "find By Form Prog Id", produces = {
            MediaType.APPLICATION_JSON }, consumes = { MediaType.APPLICATION_JSON })
    List<ScoringFormulaVo> findByFormProgId(
            @ApiQueryParam(name = "formProgId") @QueryParam("formProgId") String formProgId);
}
