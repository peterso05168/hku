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
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;

import org.jsondoc.core.annotation.Api;
import org.jsondoc.core.annotation.ApiBodyObject;
import org.jsondoc.core.annotation.ApiMethod;
import org.jsondoc.core.annotation.ApiPathParam;
import org.jsondoc.core.annotation.ApiQueryParam;
import org.jsondoc.core.annotation.ApiResponseObject;
import org.jsondoc.core.pojo.ApiVerb;
import org.springframework.data.domain.Page;

import com.accentrix.hku.jaxb.PageAdapter;
import com.accentrix.hku.vo.staff.RoleForm;
import com.accentrix.hku.vo.staff.RoleVo;

/**
 * @author Jaye.Lin
 * @date 创建时间：2018年1月31日 下午2:53:15
 * @version 1.0
 */

@Consumes({ MediaType.APPLICATION_JSON, MediaType.TEXT_XML })
@Produces({ MediaType.APPLICATION_JSON, MediaType.TEXT_XML })
@Api(name = "Role Service", description = "roleService")
public interface RoleService {

    @GET
    @Path("get")
    @ApiMethod(path = "/api/rest/role/get/", verb = ApiVerb.GET, description = "get role by id", produces = {
            MediaType.APPLICATION_JSON }, consumes = { MediaType.APPLICATION_JSON })
    @ApiResponseObject
    RoleVo get(@ApiQueryParam(name = "id", description = "The role id") @QueryParam("id") String id);

    @POST
    @Path("/")
    @ApiMethod(path = "/api/rest/role/", verb = ApiVerb.POST, description = "save role", produces = {
            MediaType.APPLICATION_JSON }, consumes = { MediaType.APPLICATION_JSON })
    @ApiResponseObject
    RoleVo save(@ApiBodyObject RoleVo vo);

    @POST
    @Path("/save-list/")
    @ApiMethod(path = "/api/rest/role/save-list/", verb = ApiVerb.POST, description = "save role list", produces = {
            MediaType.APPLICATION_JSON }, consumes = { MediaType.APPLICATION_JSON })
    @ApiResponseObject
    List<RoleVo> save(List<RoleVo> vos);

    @DELETE
    @Path("/{id}/")
    @ApiMethod(path = "/api/rest/role/{id}/", verb = ApiVerb.DELETE, description = "delete role by id", produces = {
            MediaType.APPLICATION_JSON }, consumes = { MediaType.APPLICATION_JSON })
    void delete(@ApiPathParam(name = "id", description = "The role Id") @PathParam("id") String id);

    @POST
    @Path("/delete-province/")
    @ApiMethod(path = "/api/rest/role/delete-role/", verb = ApiVerb.POST, description = "delete role", produces = {
            MediaType.APPLICATION_JSON }, consumes = { MediaType.APPLICATION_JSON })
    void delete(@ApiBodyObject RoleVo vo);

    @POST
    @Path("/findList/")
    @ApiMethod(path = "/api/rest/role/findList/", verb = ApiVerb.POST, description = "findList", produces = {
            MediaType.APPLICATION_JSON }, consumes = { MediaType.APPLICATION_JSON })
    List<RoleVo> findList();

    @POST
    @Path("/findPage/")
    @ApiMethod(path = "/api/rest/role/findPage/", verb = ApiVerb.POST, description = "findPage", produces = {
            MediaType.APPLICATION_JSON }, consumes = { MediaType.APPLICATION_JSON })
    @XmlJavaTypeAdapter(PageAdapter.class)
    Page<RoleVo> findPage(@ApiBodyObject RoleForm roleForm);

    @POST
    @Path("/findVos/")
    @ApiMethod(path = "/api/rest/role/findVos/", verb = ApiVerb.POST, description = "findVos", produces = {
            MediaType.APPLICATION_JSON }, consumes = { MediaType.APPLICATION_JSON })
    List<RoleVo> findVos(@ApiQueryParam(name = "staffId") @QueryParam("staffId") String staffId);
}
