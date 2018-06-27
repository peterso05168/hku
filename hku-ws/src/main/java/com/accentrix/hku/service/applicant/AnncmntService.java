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

import com.accentrix.hku.vo.applicant.AnncmntVo;

/** 
 * @author Jaye.Lin 
 * @date 创建时间：2018年1月31日 下午2:29:33 
 * @version 1.0 
 */

@Consumes({ MediaType.APPLICATION_JSON, MediaType.TEXT_XML })
@Produces({ MediaType.APPLICATION_JSON, MediaType.TEXT_XML })
@Api(name = "Anncmnt Service", description = "anncmntService")
public interface AnncmntService {

    @GET
    @Path("get")
    @ApiMethod(path = "/api/rest/anncmnt/get/", verb = ApiVerb.GET, description = "get anncmnt by id", produces = {
            MediaType.APPLICATION_JSON }, consumes = { MediaType.APPLICATION_JSON })
    @ApiResponseObject
    AnncmntVo get(@ApiQueryParam(name = "id", description = "The anncmnt id") @QueryParam("id") String id);

    @POST
    @Path("/")
    @ApiMethod(path = "/api/rest/anncmnt/", verb = ApiVerb.POST, description = "save anncmnt", produces = {
            MediaType.APPLICATION_JSON }, consumes = { MediaType.APPLICATION_JSON })
    @ApiResponseObject
    AnncmntVo save(@ApiBodyObject AnncmntVo vo);

    @POST
    @Path("/save-list/")
    @ApiMethod(path = "/api/rest/anncmnt/save-list/", verb = ApiVerb.POST, description = "save anncmnt list", produces = {
            MediaType.APPLICATION_JSON }, consumes = { MediaType.APPLICATION_JSON })
    @ApiResponseObject
    List<AnncmntVo> save(List<AnncmntVo> vos);

    @DELETE
    @Path("/{id}/")
    @ApiMethod(path = "/api/rest/anncmnt/{id}/", verb = ApiVerb.DELETE, description = "delete anncmnt by id", produces = {
            MediaType.APPLICATION_JSON }, consumes = { MediaType.APPLICATION_JSON })
    void delete(@ApiPathParam(name = "id", description = "The anncmnt Id") @PathParam("id") String id);

    @POST
    @Path("/delete-province/")
    @ApiMethod(path = "/api/rest/anncmnt/delete-anncmnt/", verb = ApiVerb.POST, description = "delete anncmnt", produces = {
            MediaType.APPLICATION_JSON }, consumes = { MediaType.APPLICATION_JSON })
    void delete(@ApiBodyObject AnncmntVo vo);

    @POST
    @Path("/findList/")
    @ApiMethod(path = "/api/rest/anncmnt/findList/", verb = ApiVerb.POST, description = "findList", produces = {
            MediaType.APPLICATION_JSON }, consumes = { MediaType.APPLICATION_JSON })
    List<AnncmntVo> findList();

    @POST
    @Path("/findByApplicantAccountId/")
    @ApiMethod(path = "/api/rest/anncmnt/findByApplicantAccountId/", verb = ApiVerb.POST, description = "findByApplicantAccountId", produces = {
            MediaType.APPLICATION_JSON }, consumes = { MediaType.APPLICATION_JSON })
    List<AnncmntVo> findByApplicantAccountId(
            @ApiQueryParam(name = "applicantAccountId") @QueryParam("applicantAccountId") String applicantAccountId);

    @GET
    @Path("getByTypeCdAndAccountIdAndApplicationNo")
    @ApiMethod(path = "/api/rest/anncmnt/getByTypeCdAndAccountIdAndApplicationNo/", verb = ApiVerb.GET, description = "get by typeCd,accountId,appNo", produces = {
            MediaType.APPLICATION_JSON }, consumes = { MediaType.APPLICATION_JSON })
    @ApiResponseObject
    AnncmntVo getByTypeCdAndAccountIdAndApplicationNo(
            @ApiQueryParam(name = "typeCd", description = "The anncmnt typeCd") @QueryParam("typeCd") String typeCd,
            @ApiQueryParam(name = "accountId", description = "The application account id") @QueryParam("accountId") String accountId,
            @ApiQueryParam(name = "applicationNo", description = "The application no") @QueryParam("applicationNo") String applicationNo);

    @POST
    @Path("/saveAnncmnt/")
    @ApiMethod(path = "/api/rest/anncmnt/saveAnncmnt/", verb = ApiVerb.POST, description = "saveAnncmnt", produces = {
            MediaType.APPLICATION_JSON }, consumes = { MediaType.APPLICATION_JSON })
    void saveAnncmnt(@ApiQueryParam(name = "typeCd") @QueryParam("typeCd") String typeCd,
            @ApiQueryParam(name = "value") @QueryParam("value") String value,
            @ApiQueryParam(name = "statusCd") @QueryParam("statusCd") String statusCd,
            @ApiQueryParam(name = "accountId") @QueryParam("accountId") String accountId,
            @ApiQueryParam(name = "applicationNo") @QueryParam("applicationNo") String applicationNo,
            @ApiQueryParam(name = "msgContent") @QueryParam("msgContent") String msgContent,
            @ApiQueryParam(name = "applicationId") @QueryParam("applicationId") String applicationId);
}
