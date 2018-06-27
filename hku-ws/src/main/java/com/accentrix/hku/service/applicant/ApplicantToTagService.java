package com.accentrix.hku.service.applicant;

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

import com.accentrix.hku.vo.applicant.ApplicantToTagVo;

/** 
 * @author Jaye.Lin 
 * @date 创建时间：2018年4月27日 下午5:56:26 
 * @version 1.0 
 */

@Consumes({ MediaType.APPLICATION_JSON, MediaType.TEXT_XML })
@Produces({ MediaType.APPLICATION_JSON, MediaType.TEXT_XML })
@Api(name = "ApplicantToTag Service", description = "applicantToTagService")
public interface ApplicantToTagService {

    @GET
    @Path("get")
    @ApiMethod(path = "/api/rest/applicantToTag/get/", verb = ApiVerb.GET, description = "get applicantToTag by id", produces = {
            MediaType.APPLICATION_JSON }, consumes = { MediaType.APPLICATION_JSON })
    @ApiResponseObject
    ApplicantToTagVo get(
            @ApiQueryParam(name = "id", description = "The applicantToTag id") @QueryParam("id") String id);

    @POST
    @Path("/")
    @ApiMethod(path = "/api/rest/applicantToTag/", verb = ApiVerb.POST, description = "save applicantToTag", produces = {
            MediaType.APPLICATION_JSON }, consumes = { MediaType.APPLICATION_JSON })
    @ApiResponseObject
    ApplicantToTagVo save(@ApiBodyObject ApplicantToTagVo vo);

    @POST
    @Path("/save-list/")
    @ApiMethod(path = "/api/rest/applicantToTag/save-list/", verb = ApiVerb.POST, description = "save applicantToTag list", produces = {
            MediaType.APPLICATION_JSON }, consumes = { MediaType.APPLICATION_JSON })
    @ApiResponseObject
    List<ApplicantToTagVo> save(List<ApplicantToTagVo> vos);

    @DELETE
    @Path("/{id}/")
    @ApiMethod(path = "/api/rest/applicantToTag/{id}/", verb = ApiVerb.DELETE, description = "delete applicantToTag by id", produces = {
            MediaType.APPLICATION_JSON }, consumes = { MediaType.APPLICATION_JSON })
    void delete(@ApiPathParam(name = "id", description = "The applicantToTag Id") @PathParam("id") String id);

    @POST
    @Path("/delete-province/")
    @ApiMethod(path = "/api/rest/applicantToTag/delete-applicantToTag/", verb = ApiVerb.POST, description = "delete applicantToTag", produces = {
            MediaType.APPLICATION_JSON }, consumes = { MediaType.APPLICATION_JSON })
    void delete(@ApiBodyObject ApplicantToTagVo vo);

    @POST
    @Path("/findList/")
    @ApiMethod(path = "/api/rest/applicantToTag/findList/", verb = ApiVerb.POST, description = "findList", produces = {
            MediaType.APPLICATION_JSON }, consumes = { MediaType.APPLICATION_JSON })
    List<ApplicantToTagVo> findList();

    @POST
    @Path("/findByApplicantId/")
    @ApiMethod(path = "/api/rest/applicantToTag/findByApplicantId/", verb = ApiVerb.POST, description = "findByApplicantId", produces = {
            MediaType.APPLICATION_JSON }, consumes = { MediaType.APPLICATION_JSON })
    List<ApplicantToTagVo> findByApplicantId(
            @ApiPathParam(name = "applicantId") @QueryParam("applicantId") String applicantId);
}
