package com.accentrix.hku.service.audit;

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

import com.accentrix.hku.vo.audit.AuditLogVo;

/**
 * @author Jaye.Lin
 * @date 创建时间：2018年3月5日 下午2:13:52
 * @version 1.0
 */

@Consumes({ MediaType.APPLICATION_JSON, MediaType.TEXT_XML })
@Produces({ MediaType.APPLICATION_JSON, MediaType.TEXT_XML })
@Api(name = "AuditLog Service", description = "auditLogService")
public interface AuditLogService {

    @GET
    @Path("get")
    @ApiMethod(path = "/api/rest/auditLog/get/", verb = ApiVerb.GET, description = "get auditLog by id", produces = {
            MediaType.APPLICATION_JSON }, consumes = { MediaType.APPLICATION_JSON })
    @ApiResponseObject
    AuditLogVo get(@ApiQueryParam(name = "id", description = "The auditLog id") @QueryParam("id") String id);

    @POST
    @Path("/")
    @ApiMethod(path = "/api/rest/auditLog/", verb = ApiVerb.POST, description = "save auditLog", produces = {
            MediaType.APPLICATION_JSON }, consumes = { MediaType.APPLICATION_JSON })
    @ApiResponseObject
    AuditLogVo save(@ApiBodyObject AuditLogVo vo);

    @POST
    @Path("/save-list/")
    @ApiMethod(path = "/api/rest/auditLog/save-list/", verb = ApiVerb.POST, description = "save auditLog list", produces = {
            MediaType.APPLICATION_JSON }, consumes = { MediaType.APPLICATION_JSON })
    @ApiResponseObject
    List<AuditLogVo> save(List<AuditLogVo> vos);

    @DELETE
    @Path("/{id}/")
    @ApiMethod(path = "/api/rest/auditLog/{id}/", verb = ApiVerb.DELETE, description = "delete auditLog by id", produces = {
            MediaType.APPLICATION_JSON }, consumes = { MediaType.APPLICATION_JSON })
    void delete(@ApiPathParam(name = "id", description = "The auditLog Id") @PathParam("id") String id);

    @POST
    @Path("/delete-province/")
    @ApiMethod(path = "/api/rest/auditLog/delete-auditLog/", verb = ApiVerb.POST, description = "delete auditLog", produces = {
            MediaType.APPLICATION_JSON }, consumes = { MediaType.APPLICATION_JSON })
    void delete(@ApiBodyObject AuditLogVo vo);

    @POST
    @Path("/findList/")
    @ApiMethod(path = "/api/rest/auditLog/findList/", verb = ApiVerb.POST, description = "findList", produces = {
            MediaType.APPLICATION_JSON }, consumes = { MediaType.APPLICATION_JSON })
    List<AuditLogVo> findList();

    @POST
    @Path("/getByApplicantAccountId/")
    @ApiMethod(path = "/api/rest/auditLog/getByApplicantAccountId/", verb = ApiVerb.POST, description = "getByApplicantAccountId", produces = {
            MediaType.APPLICATION_JSON }, consumes = { MediaType.APPLICATION_JSON })
    List<AuditLogVo> getByApplicantAccountId(
            @ApiQueryParam(name = "applicantAccountId") @QueryParam("applicantAccountId") String applicantAccountId);
}
