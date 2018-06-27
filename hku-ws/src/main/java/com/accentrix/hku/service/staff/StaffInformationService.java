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
import com.accentrix.hku.vo.staff.StaffInformationForm;
import com.accentrix.hku.vo.staff.StaffInformationVo;

/**
 * @author Jaye.Lin
 * @date 创建时间：2018年1月31日 下午2:55:49
 * @version 1.0
 */

@Consumes({ MediaType.APPLICATION_JSON, MediaType.TEXT_XML })
@Produces({ MediaType.APPLICATION_JSON, MediaType.TEXT_XML })
@Api(name = "StaffInformation Service", description = "staffInformationService")
public interface StaffInformationService {

    @GET
    @Path("get")
    @ApiMethod(path = "/api/rest/staffInformation/get/", verb = ApiVerb.GET, description = "get staffInformation by id", produces = {
            MediaType.APPLICATION_JSON }, consumes = { MediaType.APPLICATION_JSON })
    @ApiResponseObject
    StaffInformationVo get(
            @ApiQueryParam(name = "id", description = "The staffInformation id") @QueryParam("id") String id);

    @POST
    @Path("/")
    @ApiMethod(path = "/api/rest/staffInformation/", verb = ApiVerb.POST, description = "save staffInformation", produces = {
            MediaType.APPLICATION_JSON }, consumes = { MediaType.APPLICATION_JSON })
    @ApiResponseObject
    StaffInformationVo save(@ApiBodyObject StaffInformationVo vo);

    @POST
    @Path("/save-list/")
    @ApiMethod(path = "/api/rest/staffInformation/save-list/", verb = ApiVerb.POST, description = "save staffInformation list", produces = {
            MediaType.APPLICATION_JSON }, consumes = { MediaType.APPLICATION_JSON })
    @ApiResponseObject
    List<StaffInformationVo> save(List<StaffInformationVo> vos);

    @DELETE
    @Path("/{id}/")
    @ApiMethod(path = "/api/rest/staffInformation/{id}/", verb = ApiVerb.DELETE, description = "delete staffInformation by id", produces = {
            MediaType.APPLICATION_JSON }, consumes = { MediaType.APPLICATION_JSON })
    void delete(@ApiPathParam(name = "id", description = "The staffInformation Id") @PathParam("id") String id);

    @POST
    @Path("/delete-province/")
    @ApiMethod(path = "/api/rest/staffInformation/delete-staffInformation/", verb = ApiVerb.POST, description = "delete staffInformation", produces = {
            MediaType.APPLICATION_JSON }, consumes = { MediaType.APPLICATION_JSON })
    void delete(@ApiBodyObject StaffInformationVo vo);

    @POST
    @Path("/findList/")
    @ApiMethod(path = "/api/rest/staffInformation/findList/", verb = ApiVerb.POST, description = "findList", produces = {
            MediaType.APPLICATION_JSON }, consumes = { MediaType.APPLICATION_JSON })
    List<StaffInformationVo> findList();

    @POST
    @Path("/checkInformation/")
    @ApiMethod(path = "/api/rest/staffInformation/checkInformation/", verb = ApiVerb.POST, description = "checkInformation", produces = {
            MediaType.APPLICATION_JSON }, consumes = { MediaType.APPLICATION_JSON })
    boolean checkInformation(@ApiQueryParam(name = "email") @QueryParam("email") String email,
            @ApiQueryParam(name = "no") @QueryParam("no") String no);

    @POST
    @Path("/findPage/")
    @ApiMethod(path = "/api/rest/staffInformation/findPage/", verb = ApiVerb.POST, description = "findPage", produces = {
            MediaType.APPLICATION_JSON }, consumes = { MediaType.APPLICATION_JSON })
    @XmlJavaTypeAdapter(PageAdapter.class)
    Page<StaffInformationVo> findPage(@ApiBodyObject StaffInformationForm staffInformationForm);

    @POST
    @Path("/findByEmailAndHkuNo/")
    @ApiMethod(path = "/api/rest/staffInformation/findByEmailAndHkuNo/", verb = ApiVerb.POST, description = "findByEmailAndHkuNo", produces = {
            MediaType.APPLICATION_JSON }, consumes = { MediaType.APPLICATION_JSON })
    StaffInformationVo findByEmailAndHkuNo(@ApiQueryParam(name = "email") @QueryParam("email") String email,
            @ApiQueryParam(name = "hkuno") @QueryParam("hkuno") String hkuno);
}
