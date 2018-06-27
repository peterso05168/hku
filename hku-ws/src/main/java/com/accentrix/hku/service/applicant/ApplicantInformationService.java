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
import com.accentrix.hku.vo.applicant.ApplicantInformationForm;
import com.accentrix.hku.vo.applicant.ApplicantInformationVo;

/** 
 * @author Jaye.Lin 
 * @date 创建时间：2018年1月31日 下午2:30:11 
 * @version 1.0 
 */

@Consumes({ MediaType.APPLICATION_JSON, MediaType.TEXT_XML })
@Produces({ MediaType.APPLICATION_JSON, MediaType.TEXT_XML })
@Api(name = "ApplicantInformation Service", description = "applicantInformationService")
public interface ApplicantInformationService {

    @GET
    @Path("get")
    @ApiMethod(path = "/api/rest/applicantInformation/get/", verb = ApiVerb.GET, description = "get applicantInformation by id", produces = {
            MediaType.APPLICATION_JSON }, consumes = { MediaType.APPLICATION_JSON })
    @ApiResponseObject
    ApplicantInformationVo get(
            @ApiQueryParam(name = "id", description = "The applicantInformation id") @QueryParam("id") String id);

    @POST
    @Path("/")
    @ApiMethod(path = "/api/rest/applicantInformation/", verb = ApiVerb.POST, description = "save applicantInformation", produces = {
            MediaType.APPLICATION_JSON }, consumes = { MediaType.APPLICATION_JSON })
    @ApiResponseObject
    ApplicantInformationVo save(@ApiBodyObject ApplicantInformationVo vo);

    @POST
    @Path("/save-list/")
    @ApiMethod(path = "/api/rest/applicantInformation/save-list/", verb = ApiVerb.POST, description = "save applicantInformation list", produces = {
            MediaType.APPLICATION_JSON }, consumes = { MediaType.APPLICATION_JSON })
    @ApiResponseObject
    List<ApplicantInformationVo> save(List<ApplicantInformationVo> vos);

    @DELETE
    @Path("/{id}/")
    @ApiMethod(path = "/api/rest/applicantInformation/{id}/", verb = ApiVerb.DELETE, description = "delete applicantInformation by id", produces = {
            MediaType.APPLICATION_JSON }, consumes = { MediaType.APPLICATION_JSON })
    void delete(@ApiPathParam(name = "id", description = "The applicantInformation Id") @PathParam("id") String id);

    @POST
    @Path("/delete-province/")
    @ApiMethod(path = "/api/rest/applicantInformation/delete-applicantInformation/", verb = ApiVerb.POST, description = "delete applicantInformation", produces = {
            MediaType.APPLICATION_JSON }, consumes = { MediaType.APPLICATION_JSON })
    void delete(@ApiBodyObject ApplicantInformationVo vo);

    @POST
    @Path("/findList/")
    @ApiMethod(path = "/api/rest/applicantInformation/findList/", verb = ApiVerb.POST, description = "findList", produces = {
            MediaType.APPLICATION_JSON }, consumes = { MediaType.APPLICATION_JSON })
    List<ApplicantInformationVo> findList();

    @POST
    @Path("/findPage/")
    @ApiMethod(path = "/api/rest/applicantInformation/findPage/", verb = ApiVerb.POST, description = "findPage", produces = {
            MediaType.APPLICATION_JSON }, consumes = { MediaType.APPLICATION_JSON })
    @XmlJavaTypeAdapter(PageAdapter.class)
    Page<ApplicantInformationVo> findPage(@ApiBodyObject ApplicantInformationForm applicantInformationForm);

    @GET
    @Path("/getById/")
    @ApiMethod(path = "/api/rest/applicantInformation/getById/", verb = ApiVerb.GET, description = "getById", produces = {
            MediaType.APPLICATION_JSON }, consumes = { MediaType.APPLICATION_JSON })
    ApplicantInformationVo getById(@ApiQueryParam(name = "id") @QueryParam("id") String id);
}
