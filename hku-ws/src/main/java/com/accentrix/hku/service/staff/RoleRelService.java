package com.accentrix.hku.service.staff;

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

import com.accentrix.hku.vo.staff.RoleRelVo;

/** 
 * @author Jaye.Lin 
 * @date 创建时间：2018年3月15日 上午11:09:02 
 * @version 1.0 
 */

@Consumes({ MediaType.APPLICATION_JSON, MediaType.TEXT_XML })
@Produces({ MediaType.APPLICATION_JSON, MediaType.TEXT_XML })
@Api(name = "RoleRel Service", description = "roleRelService")
public interface RoleRelService {

    @GET
    @Path("get")
    @ApiMethod(path = "/api/rest/roleRel/get/", verb = ApiVerb.GET, description = "get roleRel by id", produces = {
            MediaType.APPLICATION_JSON }, consumes = { MediaType.APPLICATION_JSON })
    @ApiResponseObject
    RoleRelVo get(@ApiQueryParam(name = "id", description = "The roleRel id") @QueryParam("id") String id);

    @POST
    @Path("/")
    @ApiMethod(path = "/api/rest/roleRel/", verb = ApiVerb.POST, description = "save roleRel", produces = {
            MediaType.APPLICATION_JSON }, consumes = { MediaType.APPLICATION_JSON })
    @ApiResponseObject
    RoleRelVo save(@ApiBodyObject RoleRelVo vo);

    @POST
    @Path("/save-list/")
    @ApiMethod(path = "/api/rest/roleRel/save-list/", verb = ApiVerb.POST, description = "save roleRel list", produces = {
            MediaType.APPLICATION_JSON }, consumes = { MediaType.APPLICATION_JSON })
    @ApiResponseObject
    List<RoleRelVo> save(List<RoleRelVo> vos);

    @DELETE
    @Path("/{id}/")
    @ApiMethod(path = "/api/rest/roleRel/{id}/", verb = ApiVerb.DELETE, description = "delete roleRel by id", produces = {
            MediaType.APPLICATION_JSON }, consumes = { MediaType.APPLICATION_JSON })
    void delete(@ApiPathParam(name = "id", description = "The roleRel Id") @PathParam("id") String id);

    @POST
    @Path("/delete-province/")
    @ApiMethod(path = "/api/rest/roleRel/delete-roleRel/", verb = ApiVerb.POST, description = "delete roleRel", produces = {
            MediaType.APPLICATION_JSON }, consumes = { MediaType.APPLICATION_JSON })
    void delete(@ApiBodyObject RoleRelVo vo);

    @POST
    @Path("/findList/")
    @ApiMethod(path = "/api/rest/roleRel/findList/", verb = ApiVerb.POST, description = "findList", produces = {
            MediaType.APPLICATION_JSON }, consumes = { MediaType.APPLICATION_JSON })
    List<RoleRelVo> findList();

    @POST
    @Path("/deleteByStaffId/")
    @ApiMethod(path = "/api/rest/roleRel/deleteByStaffId/", verb = ApiVerb.POST, description = "deleteByStaffId", produces = {
            MediaType.APPLICATION_JSON }, consumes = { MediaType.APPLICATION_JSON })
    void deleteByStaffId(@ApiQueryParam(name = "staffId") @QueryParam("staffId") String staffId);
}
