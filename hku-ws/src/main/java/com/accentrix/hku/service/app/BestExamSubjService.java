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

import com.accentrix.hku.vo.app.BestExamSubjVo;

/** 
 * @author Jaye.Lin 
 * @date 创建时间：2018年5月29日 下午8:13:09 
 * @version 1.0 
 */

@Consumes({ MediaType.APPLICATION_JSON, MediaType.TEXT_XML })
@Produces({ MediaType.APPLICATION_JSON, MediaType.TEXT_XML })
@Api(name = "BestExamSubj Service", description = "bestExamSubjService")
public interface BestExamSubjService {

    @GET
    @Path("get")
    @ApiMethod(path = "/api/rest/bestExamSubj/get/", verb = ApiVerb.GET, description = "get bestExamSubj by id", produces = {
            MediaType.APPLICATION_JSON }, consumes = { MediaType.APPLICATION_JSON })
    @ApiResponseObject
    BestExamSubjVo get(@ApiQueryParam(name = "id", description = "The bestExamSubj id") @QueryParam("id") String id);

    @POST
    @Path("/")
    @ApiMethod(path = "/api/rest/bestExamSubj/", verb = ApiVerb.POST, description = "save bestExamSubj", produces = {
            MediaType.APPLICATION_JSON }, consumes = { MediaType.APPLICATION_JSON })
    @ApiResponseObject
    BestExamSubjVo save(@ApiBodyObject BestExamSubjVo vo);

    @POST
    @Path("/save-list/")
    @ApiMethod(path = "/api/rest/bestExamSubj/save-list/", verb = ApiVerb.POST, description = "save bestExamSubj list", produces = {
            MediaType.APPLICATION_JSON }, consumes = { MediaType.APPLICATION_JSON })
    @ApiResponseObject
    List<BestExamSubjVo> save(List<BestExamSubjVo> vos);

    @DELETE
    @Path("/{id}/")
    @ApiMethod(path = "/api/rest/bestExamSubj/{id}/", verb = ApiVerb.DELETE, description = "delete bestExamSubj by id", produces = {
            MediaType.APPLICATION_JSON }, consumes = { MediaType.APPLICATION_JSON })
    void delete(@ApiPathParam(name = "id", description = "The bestExamSubj Id") @PathParam("id") String id);

    @POST
    @Path("/delete-province/")
    @ApiMethod(path = "/api/rest/bestExamSubj/delete-bestExamSubj/", verb = ApiVerb.POST, description = "delete bestExamSubj", produces = {
            MediaType.APPLICATION_JSON }, consumes = { MediaType.APPLICATION_JSON })
    void delete(@ApiBodyObject BestExamSubjVo vo);

    @POST
    @Path("/findList/")
    @ApiMethod(path = "/api/rest/bestExamSubj/findList/", verb = ApiVerb.POST, description = "findList", produces = {
            MediaType.APPLICATION_JSON }, consumes = { MediaType.APPLICATION_JSON })
    List<BestExamSubjVo> findList();

    @GET
    @Path("getByApplicationIdAndExamTypeIdAndCalculateType")
    @ApiMethod(path = "/api/rest/bestExamSubj/getByApplicationIdAndExamTypeIdAndCalculateType/", verb = ApiVerb.GET, description = "getByApplicationIdAndExamTypeIdAndCalculateType", produces = {
            MediaType.APPLICATION_JSON }, consumes = { MediaType.APPLICATION_JSON })
    @ApiResponseObject
    BestExamSubjVo getByApplicationIdAndExamTypeIdAndCalculateType(
            @ApiQueryParam(name = "applicationId", description = "The application id") @QueryParam("applicationId") String applicationId,
            @ApiQueryParam(name = "examTypeId", description = "The examType id") @QueryParam("examTypeId") String examTypeId,
            @ApiQueryParam(name = "calculateType", description = "The calculate type") @QueryParam("calculateType") String calculateType);
}
