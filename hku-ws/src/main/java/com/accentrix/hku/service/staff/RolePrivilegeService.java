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

import com.accentrix.hku.vo.staff.RolePrivilegeVo;

/** 
 * @author Jaye.Lin 
 * @date 创建时间：2018年1月31日 下午2:55:32 
 * @version 1.0 
 */

@Consumes({ MediaType.APPLICATION_JSON, MediaType.TEXT_XML })
@Produces({ MediaType.APPLICATION_JSON, MediaType.TEXT_XML })
@Api(name = "RolePrivilege Service", description = "rolePrivilegeService")
public interface RolePrivilegeService {

    @GET
    @Path("get")
    @ApiMethod(path = "/api/rest/rolePrivilege/get/", verb = ApiVerb.GET, description = "get rolePrivilege by id", produces = {
            MediaType.APPLICATION_JSON }, consumes = { MediaType.APPLICATION_JSON })
    @ApiResponseObject
    RolePrivilegeVo get(@ApiQueryParam(name = "id", description = "The rolePrivilege id") @QueryParam("id") String id);

    @POST
    @Path("/")
    @ApiMethod(path = "/api/rest/rolePrivilege/", verb = ApiVerb.POST, description = "save rolePrivilege", produces = {
            MediaType.APPLICATION_JSON }, consumes = { MediaType.APPLICATION_JSON })
    @ApiResponseObject
    RolePrivilegeVo save(@ApiBodyObject RolePrivilegeVo vo);

    @POST
    @Path("/save-list/")
    @ApiMethod(path = "/api/rest/rolePrivilege/save-list/", verb = ApiVerb.POST, description = "save rolePrivilege list", produces = {
            MediaType.APPLICATION_JSON }, consumes = { MediaType.APPLICATION_JSON })
    @ApiResponseObject
    List<RolePrivilegeVo> save(List<RolePrivilegeVo> vos);

    @DELETE
    @Path("/{id}/")
    @ApiMethod(path = "/api/rest/rolePrivilege/{id}/", verb = ApiVerb.DELETE, description = "delete rolePrivilege by id", produces = {
            MediaType.APPLICATION_JSON }, consumes = { MediaType.APPLICATION_JSON })
    void delete(@ApiPathParam(name = "id", description = "The rolePrivilege Id") @PathParam("id") String id);

    @POST
    @Path("/delete-province/")
    @ApiMethod(path = "/api/rest/rolePrivilege/delete-rolePrivilege/", verb = ApiVerb.POST, description = "delete rolePrivilege", produces = {
            MediaType.APPLICATION_JSON }, consumes = { MediaType.APPLICATION_JSON })
    void delete(@ApiBodyObject RolePrivilegeVo vo);

    @POST
    @Path("/findList/")
    @ApiMethod(path = "/api/rest/rolePrivilege/findList/", verb = ApiVerb.POST, description = "findList", produces = {
            MediaType.APPLICATION_JSON }, consumes = { MediaType.APPLICATION_JSON })
    List<RolePrivilegeVo> findList();

    @POST
    @Path("/saveRolePrivilege/")
    @ApiMethod(path = "/api/rest/rolePrivilege/saveRolePrivilege/", verb = ApiVerb.POST, description = "saveRolePrivilege", produces = {
            MediaType.APPLICATION_JSON }, consumes = { MediaType.APPLICATION_JSON })
    void saveRolePrivilege(@ApiQueryParam(name = "roleId") @QueryParam("roleId") String roleId,
            @ApiQueryParam(name = "privilegeIds") @QueryParam("privilegeIds") List<String> privilegeIds);
}
