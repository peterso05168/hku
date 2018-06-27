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

/** 
 * @author Jaye.Lin 
 * @date 创建时间：2018年5月29日 下午8:02:28 
 * @version 1.0 
 */

@Consumes({ MediaType.APPLICATION_JSON, MediaType.TEXT_XML })
@Produces({ MediaType.APPLICATION_JSON, MediaType.TEXT_XML })
@Api(name = "BestExamSubjRslt Service", description = "bestExamSubjRsltService")
public interface BestExamSubjRsltService {

    @GET
    @Path("get")
    @ApiMethod(path = "/api/rest/bestExamSubjRslt/get/", verb = ApiVerb.GET, description = "get bestExamSubjRslt by id", produces = {
            MediaType.APPLICATION_JSON }, consumes = { MediaType.APPLICATION_JSON })
    @ApiResponseObject
    BestExamSubjRsltVo get(
            @ApiQueryParam(name = "id", description = "The bestExamSubjRslt id") @QueryParam("id") String id);

    @POST
    @Path("/")
    @ApiMethod(path = "/api/rest/bestExamSubjRslt/", verb = ApiVerb.POST, description = "save bestExamSubjRslt", produces = {
            MediaType.APPLICATION_JSON }, consumes = { MediaType.APPLICATION_JSON })
    @ApiResponseObject
    BestExamSubjRsltVo save(@ApiBodyObject BestExamSubjRsltVo vo);

    @POST
    @Path("/save-list/")
    @ApiMethod(path = "/api/rest/bestExamSubjRslt/save-list/", verb = ApiVerb.POST, description = "save bestExamSubjRslt list", produces = {
            MediaType.APPLICATION_JSON }, consumes = { MediaType.APPLICATION_JSON })
    @ApiResponseObject
    List<BestExamSubjRsltVo> save(List<BestExamSubjRsltVo> vos);

    @DELETE
    @Path("/{id}/")
    @ApiMethod(path = "/api/rest/bestExamSubjRslt/{id}/", verb = ApiVerb.DELETE, description = "delete bestExamSubjRslt by id", produces = {
            MediaType.APPLICATION_JSON }, consumes = { MediaType.APPLICATION_JSON })
    void delete(@ApiPathParam(name = "id", description = "The bestExamSubjRslt Id") @PathParam("id") String id);

    @POST
    @Path("/delete-province/")
    @ApiMethod(path = "/api/rest/bestExamSubjRslt/delete-bestExamSubjRslt/", verb = ApiVerb.POST, description = "delete bestExamSubjRslt", produces = {
            MediaType.APPLICATION_JSON }, consumes = { MediaType.APPLICATION_JSON })
    void delete(@ApiBodyObject BestExamSubjRsltVo vo);

    @POST
    @Path("/findList/")
    @ApiMethod(path = "/api/rest/bestExamSubjRslt/findList/", verb = ApiVerb.POST, description = "findList", produces = {
            MediaType.APPLICATION_JSON }, consumes = { MediaType.APPLICATION_JSON })
    List<BestExamSubjRsltVo> findList();

    @POST
    @Path("/getBestExamSubjects/")
    @ApiMethod(path = "/api/rest/bestExamSubjRslt/getBestExamSubjects/", verb = ApiVerb.POST, description = "getBestExamSubjects", produces = {
            MediaType.APPLICATION_JSON }, consumes = { MediaType.APPLICATION_JSON })
    List<BestExamSubjRsltVo> getBestExamSubjects(
            @ApiQueryParam(name = "examCd", description = "The exam code") @QueryParam("examCd") String examCd,
            @ApiQueryParam(name = "applicationId", description = "The application id") @QueryParam("applicationId") String applicationId,
            @ApiQueryParam(name = "calculateType", description = "The calculate type") @QueryParam("calculateType") String calculateType);

    @POST
    @Path("/findByBestExamSubjId/")
    @ApiMethod(path = "/api/rest/bestExamSubjRslt/findByBestExamSubjId/", verb = ApiVerb.POST, description = "findByBestExamSubjId", produces = {
            MediaType.APPLICATION_JSON }, consumes = { MediaType.APPLICATION_JSON })
    List<BestExamSubjRsltVo> findByBestExamSubjId(
            @ApiQueryParam(name = "bestExamSubjId", description = "The bestExamSubj id") @QueryParam("bestExamSubjId") String bestExamSubjId);
}
