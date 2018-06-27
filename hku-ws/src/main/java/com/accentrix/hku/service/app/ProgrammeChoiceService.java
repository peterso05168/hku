package com.accentrix.hku.service.app;

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
import com.accentrix.hku.vo.app.ProgrammeChoiceForm;
import com.accentrix.hku.vo.app.ProgrammeChoiceVo;

/**
 * @author Jaye.Lin
 * @date 创建时间：2018年1月31日 下午2:23:45
 * @version 1.0
 */

@Consumes({ MediaType.APPLICATION_JSON, MediaType.TEXT_XML })
@Produces({ MediaType.APPLICATION_JSON, MediaType.TEXT_XML })
@Api(name = "Programme Choice Service", description = "programmeChoiceService")
public interface ProgrammeChoiceService {

    @GET
    @Path("get")
    @ApiMethod(path = "/api/rest/programmeChoice/get/", verb = ApiVerb.GET, description = "get programmeChoice by id", produces = {
            MediaType.APPLICATION_JSON }, consumes = { MediaType.APPLICATION_JSON })
    @ApiResponseObject
    ProgrammeChoiceVo get(
            @ApiQueryParam(name = "id", description = "The programmeChoice id") @QueryParam("id") String id);

    @POST
    @Path("/")
    @ApiMethod(path = "/api/rest/programmeChoice/", verb = ApiVerb.POST, description = "save programmeChoice", produces = {
            MediaType.APPLICATION_JSON }, consumes = { MediaType.APPLICATION_JSON })
    @ApiResponseObject
    ProgrammeChoiceVo save(@ApiBodyObject ProgrammeChoiceVo vo);

    @POST
    @Path("/save-list/")
    @ApiMethod(path = "/api/rest/programmeChoice/save-list/", verb = ApiVerb.POST, description = "save programmeChoice list", produces = {
            MediaType.APPLICATION_JSON }, consumes = { MediaType.APPLICATION_JSON })
    @ApiResponseObject
    List<ProgrammeChoiceVo> save(List<ProgrammeChoiceVo> vos);

    @DELETE
    @Path("/{id}/")
    @ApiMethod(path = "/api/rest/programmeChoice/{id}/", verb = ApiVerb.DELETE, description = "delete programmeChoice by id", produces = {
            MediaType.APPLICATION_JSON }, consumes = { MediaType.APPLICATION_JSON })
    void delete(@ApiPathParam(name = "id", description = "The programmeChoice Id") @PathParam("id") String id);

    @POST
    @Path("/delete-province/")
    @ApiMethod(path = "/api/rest/programmeChoice/delete-programmeChoice/", verb = ApiVerb.POST, description = "delete programmeChoice", produces = {
            MediaType.APPLICATION_JSON }, consumes = { MediaType.APPLICATION_JSON })
    void delete(@ApiBodyObject ProgrammeChoiceVo vo);

    @POST
    @Path("/findList/")
    @ApiMethod(path = "/api/rest/programmeChoice/findList/", verb = ApiVerb.POST, description = "findList", produces = {
            MediaType.APPLICATION_JSON }, consumes = { MediaType.APPLICATION_JSON })
    List<ProgrammeChoiceVo> findList();

    @GET
    @Path("/getFirstChoiceByApplicationId/")
    @ApiMethod(path = "/api/rest/programmeChoice/getFirstChoiceByApplicationId/", verb = ApiVerb.GET, description = "get programmeChoice by applicationId", produces = {
            MediaType.APPLICATION_JSON }, consumes = { MediaType.APPLICATION_JSON })
    ProgrammeChoiceVo getFirstChoiceByApplicationId(
            @ApiQueryParam(name = "applicationId") @QueryParam("applicationId") String applicationId);

    @POST
    @Path("/getOtherChoiceByApplicationId/")
    @ApiMethod(path = "/api/rest/programmeChoice/getOtherChoiceByApplicationId/", verb = ApiVerb.POST, description = "getOtherChoiceByApplicationId", produces = {
            MediaType.APPLICATION_JSON }, consumes = { MediaType.APPLICATION_JSON })
    List<ProgrammeChoiceVo> getOtherChoiceByApplicationId(
            @ApiQueryParam(name = "applicationId") @QueryParam("applicationId") String applicationId);

    @POST
    @Path("/findByHkuProgrammeId/")
    @ApiMethod(path = "/api/rest/programmeChoice/findByHkuProgrammeId/", verb = ApiVerb.POST, description = "findByHkuProgrammeId", produces = {
            MediaType.APPLICATION_JSON }, consumes = { MediaType.APPLICATION_JSON })
    List<ProgrammeChoiceVo> findByHkuProgrammeId(
            @ApiQueryParam(name = "programmeId", description = "The programme id") @QueryParam("programmeId") String programmeId);

    @POST
    @Path("/findByFormProgId/")
    @ApiMethod(path = "/api/rest/programmeChoice/findByFormProgId/", verb = ApiVerb.POST, description = "findByFormProgId", produces = {
            MediaType.APPLICATION_JSON }, consumes = { MediaType.APPLICATION_JSON })
    List<ProgrammeChoiceVo> findByFormProgId(
            @ApiQueryParam(name = "formProgId", description = "The formProg id") @QueryParam("formProgId") String formProgId);

    @POST
    @Path("/getChoiceByApplicationId/")
    @ApiMethod(path = "/api/rest/programmeChoice/getChoiceByApplicationId/", verb = ApiVerb.POST, description = "getChoiceByApplicationId", produces = {
            MediaType.APPLICATION_JSON }, consumes = { MediaType.APPLICATION_JSON })
    List<ProgrammeChoiceVo> getChoiceByApplicationId(
            @ApiQueryParam(name = "applicationId") @QueryParam("applicationId") String applicationId);

    @POST
    @Path("/findPage/")
    @ApiMethod(path = "/api/rest/programmeChoice/findPage/", verb = ApiVerb.POST, description = "findPage", produces = {
            MediaType.APPLICATION_JSON }, consumes = { MediaType.APPLICATION_JSON })
    @XmlJavaTypeAdapter(PageAdapter.class)
    Page<ProgrammeChoiceVo> findPage(@ApiBodyObject ProgrammeChoiceForm programmeChoiceForm);
}
