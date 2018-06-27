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

import com.accentrix.hku.vo.adm.GpaFormulaItmVo;

/** 
 * @author Jaye.Lin 
 * @date 创建时间：2018年3月19日 上午11:43:16 
 * @version 1.0 
 */

@Consumes({ MediaType.APPLICATION_JSON, MediaType.TEXT_XML })
@Produces({ MediaType.APPLICATION_JSON, MediaType.TEXT_XML })
@Api(name = "GpaFormulaItm Service", description = "gpaFormulaItmService")
public interface GpaFormulaItmService {

    @GET
    @Path("get")
    @ApiMethod(path = "/api/rest/gpaFormulaItm/get/", verb = ApiVerb.GET, description = "get gpaFormulaItm by id", produces = {
            MediaType.APPLICATION_JSON }, consumes = { MediaType.APPLICATION_JSON })
    @ApiResponseObject
    GpaFormulaItmVo get(@ApiQueryParam(name = "id", description = "The gpaFormulaItm id") @QueryParam("id") String id);

    @POST
    @Path("/")
    @ApiMethod(path = "/api/rest/gpaFormulaItm/", verb = ApiVerb.POST, description = "save gpaFormulaItm", produces = {
            MediaType.APPLICATION_JSON }, consumes = { MediaType.APPLICATION_JSON })
    @ApiResponseObject
    GpaFormulaItmVo save(@ApiBodyObject GpaFormulaItmVo vo);

    @POST
    @Path("/save-list/")
    @ApiMethod(path = "/api/rest/gpaFormulaItm/save-list/", verb = ApiVerb.POST, description = "save gpaFormulaItm list", produces = {
            MediaType.APPLICATION_JSON }, consumes = { MediaType.APPLICATION_JSON })
    @ApiResponseObject
    List<GpaFormulaItmVo> save(List<GpaFormulaItmVo> vos);

    @DELETE
    @Path("/{id}/")
    @ApiMethod(path = "/api/rest/gpaFormulaItm/{id}/", verb = ApiVerb.DELETE, description = "delete gpaFormulaItm by id", produces = {
            MediaType.APPLICATION_JSON }, consumes = { MediaType.APPLICATION_JSON })
    void delete(@ApiPathParam(name = "id", description = "The gpaFormulaItm Id") @PathParam("id") String id);

    @POST
    @Path("/delete-province/")
    @ApiMethod(path = "/api/rest/gpaFormulaItm/delete-gpaFormulaItm/", verb = ApiVerb.POST, description = "delete gpaFormulaItm", produces = {
            MediaType.APPLICATION_JSON }, consumes = { MediaType.APPLICATION_JSON })
    void delete(@ApiBodyObject GpaFormulaItmVo vo);

    @POST
    @Path("/findList/")
    @ApiMethod(path = "/api/rest/gpaFormulaItm/findList/", verb = ApiVerb.POST, description = "findList", produces = {
            MediaType.APPLICATION_JSON }, consumes = { MediaType.APPLICATION_JSON })
    List<GpaFormulaItmVo> findList();

    @POST
    @Path("/findByScoringFormulaId/")
    @ApiMethod(path = "/api/rest/gpaFormulaItm/findByScoringFormulaId/", verb = ApiVerb.POST, description = "findByScoringFormulaId", produces = {
            MediaType.APPLICATION_JSON }, consumes = { MediaType.APPLICATION_JSON })
    List<GpaFormulaItmVo> findByScoringFormulaId(
            @ApiQueryParam(name = "scoringFormulaId") @QueryParam("scoringFormulaId") String scoringFormulaId);
}
