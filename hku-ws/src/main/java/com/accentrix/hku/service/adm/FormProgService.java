package com.accentrix.hku.service.adm;

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

import com.accentrix.hku.vo.adm.FormProgVo;

/**
 * @author Jaye.Lin
 * @date 创建时间：2018年1月31日 下午3:12:02
 * @version 1.0
 */

@Consumes({ MediaType.APPLICATION_JSON, MediaType.TEXT_XML })
@Produces({ MediaType.APPLICATION_JSON, MediaType.TEXT_XML })
@Api(name = "Form Prog Service", description = "formProgService")
public interface FormProgService {

    @GET
    @Path("get")
    @ApiMethod(path = "/api/rest/formProg/get/", verb = ApiVerb.GET, description = "get formProg by id", produces = {
            MediaType.APPLICATION_JSON }, consumes = { MediaType.APPLICATION_JSON })
    @ApiResponseObject
    FormProgVo get(@ApiQueryParam(name = "id", description = "The formProg id") @QueryParam("id") String id);

    @POST
    @Path("/")
    @ApiMethod(path = "/api/rest/formProg/", verb = ApiVerb.POST, description = "save formProg", produces = {
            MediaType.APPLICATION_JSON }, consumes = { MediaType.APPLICATION_JSON })
    @ApiResponseObject
    FormProgVo save(@ApiBodyObject FormProgVo vo);

    @POST
    @Path("/save-list/")
    @ApiMethod(path = "/api/rest/formProg/save-list/", verb = ApiVerb.POST, description = "save formProg list", produces = {
            MediaType.APPLICATION_JSON }, consumes = { MediaType.APPLICATION_JSON })
    @ApiResponseObject
    List<FormProgVo> save(List<FormProgVo> vos);

    @DELETE
    @Path("/{id}/")
    @ApiMethod(path = "/api/rest/formProg/{id}/", verb = ApiVerb.DELETE, description = "delete formProg by id", produces = {
            MediaType.APPLICATION_JSON }, consumes = { MediaType.APPLICATION_JSON })
    void delete(@ApiPathParam(name = "id", description = "The formProg Id") @PathParam("id") String id);

    @POST
    @Path("/delete-province/")
    @ApiMethod(path = "/api/rest/formProg/delete-formProg/", verb = ApiVerb.POST, description = "delete formProg", produces = {
            MediaType.APPLICATION_JSON }, consumes = { MediaType.APPLICATION_JSON })
    void delete(@ApiBodyObject FormProgVo vo);

    //    @POST
    //    @Path("/findList/")
    //    @ApiMethod(path = "/api/rest/formProg/findList/", verb = ApiVerb.POST, description = "findList", produces = {
    //            MediaType.APPLICATION_JSON }, consumes = { MediaType.APPLICATION_JSON })
    //    List<FormProgVo> findList();

    @POST
    @Path("/getByAdmFormId/")
    @ApiMethod(path = "/api/rest/formProg/getByAdmFormId/", verb = ApiVerb.POST, description = "getByAdmFormId", produces = {
            MediaType.APPLICATION_JSON }, consumes = { MediaType.APPLICATION_JSON })
    List<FormProgVo> getByAdmFormId(@ApiQueryParam(name = "admFormId") @QueryParam("admFormId") String admFormId);

    @POST
    @Path("/basicSearch/")
    @ApiMethod(path = "/api/rest/formProg/basicSearch/", verb = ApiVerb.POST, description = "basicSearch", produces = {
            MediaType.APPLICATION_JSON }, consumes = { MediaType.APPLICATION_JSON })
    List<FormProgVo> basicSearch(@ApiQueryParam(name = "criteria") @QueryParam("criteria") String criteria);

    @POST
    @Path("/advancedSearch/")
    @ApiMethod(path = "/api/rest/formProg/advancedSearch/", verb = ApiVerb.POST, description = "advancedSearch", produces = {
            MediaType.APPLICATION_JSON }, consumes = { MediaType.APPLICATION_JSON })
    List<FormProgVo> advancedSearch(@ApiBodyObject FormProgVo searchVo);

    @POST
    @Path("/findVos/")
    @ApiMethod(path = "/api/rest/formProg/findVos/", verb = ApiVerb.POST, description = "findVos", produces = {
            MediaType.APPLICATION_JSON }, consumes = { MediaType.APPLICATION_JSON })
    List<FormProgVo> findVos(@ApiQueryParam(name = "year") @QueryParam("year") Integer year);

    @POST
    @Path("/findByHkuProgrammeId/")
    @ApiMethod(path = "/api/rest/formProg/findByHkuProgrammeId/", verb = ApiVerb.POST, description = "findByHkuProgrammeId", produces = {
            MediaType.APPLICATION_JSON }, consumes = { MediaType.APPLICATION_JSON })
    List<FormProgVo> findByHkuProgrammeId(
            @ApiQueryParam(name = "programmeId", description = "The programme id") @QueryParam("programmeId") String programmeId);
}
