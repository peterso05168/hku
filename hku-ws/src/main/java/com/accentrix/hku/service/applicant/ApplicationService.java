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

import com.accentrix.hku.vo.applicant.ApplicationVo;

/**
 * @author Jaye.Lin
 * @date 创建时间：2018年1月31日 下午2:29:51
 * @version 1.0
 */

@Consumes({ MediaType.APPLICATION_JSON, MediaType.TEXT_XML })
@Produces({ MediaType.APPLICATION_JSON, MediaType.TEXT_XML })
@Api(name = "Application Service", description = "applicationService")
public interface ApplicationService {

    @GET
    @Path("get")
    @ApiMethod(path = "/api/rest/application/get/", verb = ApiVerb.GET, description = "get application by id", produces = {
            MediaType.APPLICATION_JSON }, consumes = { MediaType.APPLICATION_JSON })
    @ApiResponseObject
    ApplicationVo get(@ApiQueryParam(name = "id", description = "The application id") @QueryParam("id") String id);

    @POST
    @Path("/")
    @ApiMethod(path = "/api/rest/application/", verb = ApiVerb.POST, description = "save application", produces = {
            MediaType.APPLICATION_JSON }, consumes = { MediaType.APPLICATION_JSON })
    @ApiResponseObject
    ApplicationVo save(@ApiBodyObject ApplicationVo vo);

    @POST
    @Path("/save-list/")
    @ApiMethod(path = "/api/rest/application/save-list/", verb = ApiVerb.POST, description = "save application list", produces = {
            MediaType.APPLICATION_JSON }, consumes = { MediaType.APPLICATION_JSON })
    @ApiResponseObject
    List<ApplicationVo> save(List<ApplicationVo> vos);

    @DELETE
    @Path("/{id}/")
    @ApiMethod(path = "/api/rest/application/{id}/", verb = ApiVerb.DELETE, description = "delete application by id", produces = {
            MediaType.APPLICATION_JSON }, consumes = { MediaType.APPLICATION_JSON })
    void delete(@ApiPathParam(name = "id", description = "The application Id") @PathParam("id") String id);

    @POST
    @Path("/delete-province/")
    @ApiMethod(path = "/api/rest/application/delete-application/", verb = ApiVerb.POST, description = "delete application", produces = {
            MediaType.APPLICATION_JSON }, consumes = { MediaType.APPLICATION_JSON })
    void delete(@ApiBodyObject ApplicationVo vo);

    @POST
    @Path("/findList/")
    @ApiMethod(path = "/api/rest/application/findList/", verb = ApiVerb.POST, description = "findList", produces = {
            MediaType.APPLICATION_JSON }, consumes = { MediaType.APPLICATION_JSON })
    List<ApplicationVo> findList();

    @POST
    @Path("/findByAdmFormIdAndAccountId/")
    @ApiMethod(path = "/api/rest/application/findByAdmFormIdAndAccountId/", verb = ApiVerb.POST, description = "findByAdmFormIdAndAccountId", produces = {
            MediaType.APPLICATION_JSON }, consumes = { MediaType.APPLICATION_JSON })
    @ApiResponseObject
    ApplicationVo findByAdmFormIdAndAccountId(
            @ApiQueryParam(name = "admFormId", description = "The admFormId") @QueryParam("admFormId") String admFormId,
            @ApiQueryParam(name = "applicantAccountId", description = "The applicantAccountId") @QueryParam("applicantAccountId") String applicantAccountId);

    @POST
    @Path("/findByAccountIds/")
    @ApiMethod(path = "/api/rest/application/findByAccountIds/", verb = ApiVerb.POST, description = "findByAccountIds", produces = {
            MediaType.APPLICATION_JSON }, consumes = { MediaType.APPLICATION_JSON })
    List<ApplicationVo> findByAccountIds(
            @ApiQueryParam(name = "applicantAccountId") @QueryParam("applicantAccountId") List<String> applicantAccountIds);

    @POST
    @Path("/basicSearch/")
    @ApiMethod(path = "/api/rest/application/basicSearch/", verb = ApiVerb.POST, description = "basicSearch", produces = {
            MediaType.APPLICATION_JSON }, consumes = { MediaType.APPLICATION_JSON })
    List<ApplicationVo> basicSearch(@ApiQueryParam(name = "criteria") @QueryParam("criteria") String criteria);

    @POST
    @Path("/advancedSearch/")
    @ApiMethod(path = "/api/rest/application/advancedSearch/", verb = ApiVerb.POST, description = "advancedSearch", produces = {
            MediaType.APPLICATION_JSON }, consumes = { MediaType.APPLICATION_JSON })
    List<ApplicationVo> advancedSearch(@ApiBodyObject ApplicationVo searchVo);

    @POST
    @Path("/findByApplicantId/")
    @ApiMethod(path = "/api/rest/application/findByApplicantId/", verb = ApiVerb.POST, description = "findByApplicantId", produces = {
            MediaType.APPLICATION_JSON }, consumes = { MediaType.APPLICATION_JSON })
    List<ApplicationVo> findByApplicantId(
            @ApiQueryParam(name = "applicantId") @QueryParam("applicantId") String applicantId);

    @POST
    @Path("/findByAccountId/")
    @ApiMethod(path = "/api/rest/application/findByAccountId/", verb = ApiVerb.POST, description = "findByAccountId", produces = {
            MediaType.APPLICATION_JSON }, consumes = { MediaType.APPLICATION_JSON })
    List<ApplicationVo> findByAccountId(
            @ApiQueryParam(name = "applicantAccountId") @QueryParam("applicantAccountId") String applicantAccountId);

    @POST
    @Path("/getByApplicationId/")
    @ApiMethod(path = "/api/rest/application/getByApplicationId/", verb = ApiVerb.POST, description = "getByApplicationId", produces = {
            MediaType.APPLICATION_JSON }, consumes = { MediaType.APPLICATION_JSON })
    ApplicationVo getByApplicationId(
            @ApiQueryParam(name = "applicationId") @QueryParam("applicationId") String applicationId);

    @POST
    @Path("/findByAccountIdAndStatus/")
    @ApiMethod(path = "/api/rest/application/findByAccountIdAndStatus/", verb = ApiVerb.POST, description = "findByAccountIdAndStatus", produces = {
            MediaType.APPLICATION_JSON }, consumes = { MediaType.APPLICATION_JSON })
    List<ApplicationVo> findByAccountIdAndStatus(
            @ApiQueryParam(name = "applicantAccountId") @QueryParam("applicantAccountId") String applicantAccountId,
            @ApiQueryParam(name = "status") @QueryParam("status") String status);
}
