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

import com.accentrix.hku.vo.staff.PrivilegeVo;

/** 
 * @author Jaye.Lin 
 * @date 创建时间：2018年3月15日 上午11:08:41 
 * @version 1.0 
 */

@Consumes({ MediaType.APPLICATION_JSON, MediaType.TEXT_XML })
@Produces({ MediaType.APPLICATION_JSON, MediaType.TEXT_XML })
@Api(name = "Privilege Service", description = "privilegeService")
public interface PrivilegeService {

    @GET
    @Path("get")
    @ApiMethod(path = "/api/rest/privilege/get/", verb = ApiVerb.GET, description = "get privilege by id", produces = {
            MediaType.APPLICATION_JSON }, consumes = { MediaType.APPLICATION_JSON })
    @ApiResponseObject
    PrivilegeVo get(@ApiQueryParam(name = "id", description = "The privilege id") @QueryParam("id") String id);

    @POST
    @Path("/")
    @ApiMethod(path = "/api/rest/privilege/", verb = ApiVerb.POST, description = "save privilege", produces = {
            MediaType.APPLICATION_JSON }, consumes = { MediaType.APPLICATION_JSON })
    @ApiResponseObject
    PrivilegeVo save(@ApiBodyObject PrivilegeVo vo);

    @POST
    @Path("/save-list/")
    @ApiMethod(path = "/api/rest/privilege/save-list/", verb = ApiVerb.POST, description = "save privilege list", produces = {
            MediaType.APPLICATION_JSON }, consumes = { MediaType.APPLICATION_JSON })
    @ApiResponseObject
    List<PrivilegeVo> save(List<PrivilegeVo> vos);

    @DELETE
    @Path("/{id}/")
    @ApiMethod(path = "/api/rest/privilege/{id}/", verb = ApiVerb.DELETE, description = "delete privilege by id", produces = {
            MediaType.APPLICATION_JSON }, consumes = { MediaType.APPLICATION_JSON })
    void delete(@ApiPathParam(name = "id", description = "The privilege Id") @PathParam("id") String id);

    @POST
    @Path("/delete-province/")
    @ApiMethod(path = "/api/rest/privilege/delete-privilege/", verb = ApiVerb.POST, description = "delete privilege", produces = {
            MediaType.APPLICATION_JSON }, consumes = { MediaType.APPLICATION_JSON })
    void delete(@ApiBodyObject PrivilegeVo vo);

    @POST
    @Path("/findList/")
    @ApiMethod(path = "/api/rest/privilege/findList/", verb = ApiVerb.POST, description = "findList", produces = {
            MediaType.APPLICATION_JSON }, consumes = { MediaType.APPLICATION_JSON })
    List<PrivilegeVo> findList();

    @POST
    @Path("/findVos/")
    @ApiMethod(path = "/api/rest/privilege/findVos/", verb = ApiVerb.POST, description = "findVos", produces = {
            MediaType.APPLICATION_JSON }, consumes = { MediaType.APPLICATION_JSON })
    List<PrivilegeVo> findVos(@ApiQueryParam(name = "roleId") @QueryParam("roleId") String roleId);

    @POST
    @Path("/findModule/")
    @ApiMethod(path = "/api/rest/privilege/findModule/", verb = ApiVerb.POST, description = "findModule", produces = {
            MediaType.APPLICATION_JSON }, consumes = { MediaType.APPLICATION_JSON })
    boolean findModule(@ApiQueryParam(name = "staffInfoId") @QueryParam("staffInfoId") String staffInfoId,
            @ApiQueryParam(name = "module") @QueryParam("module") String module,
            @ApiQueryParam(name = "moduleCode") @QueryParam("moduleCode") String moduleCode);
}
