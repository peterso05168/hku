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

import com.accentrix.hku.vo.adm.AdmAnncmntVo;

/** 
 * @author Jaye.Lin 
 * @date 创建时间：2018年1月31日 下午2:29:33 
 * @version 1.0 
 */

@Consumes({ MediaType.APPLICATION_JSON, MediaType.TEXT_XML })
@Produces({ MediaType.APPLICATION_JSON, MediaType.TEXT_XML })
@Api(name = "Anncmnt Template Service", description = "anncmntTemplateService")
public interface AnncmntTemplateService {

    @GET
    @Path("get")
    @ApiMethod(path = "/api/rest/admAnncmnt/get/", verb = ApiVerb.GET, description = "get anncmnt by id", produces = {
            MediaType.APPLICATION_JSON }, consumes = { MediaType.APPLICATION_JSON })
    @ApiResponseObject
    AdmAnncmntVo get(@ApiQueryParam(name = "id", description = "The anncmnt id") @QueryParam("id") String id);

    @POST
    @Path("/")
    @ApiMethod(path = "/api/rest/admAnncmnt/", verb = ApiVerb.POST, description = "save anncmnt", produces = {
            MediaType.APPLICATION_JSON }, consumes = { MediaType.APPLICATION_JSON })
    @ApiResponseObject
    AdmAnncmntVo save(@ApiBodyObject AdmAnncmntVo vo);

    @POST
    @Path("/save-list/")
    @ApiMethod(path = "/api/rest/admAnncmnt/save-list/", verb = ApiVerb.POST, description = "save anncmnt list", produces = {
            MediaType.APPLICATION_JSON }, consumes = { MediaType.APPLICATION_JSON })
    @ApiResponseObject
    List<AdmAnncmntVo> save(List<AdmAnncmntVo> vos);

    @DELETE
    @Path("/{id}/")
    @ApiMethod(path = "/api/rest/admAnncmnt/{id}/", verb = ApiVerb.DELETE, description = "delete anncmnt by id", produces = {
            MediaType.APPLICATION_JSON }, consumes = { MediaType.APPLICATION_JSON })
    void delete(@ApiPathParam(name = "id", description = "The anncmnt Id") @PathParam("id") String id);

    @POST
    @Path("/delete-province/")
    @ApiMethod(path = "/api/rest/admAnncmnt/delete-anncmnt/", verb = ApiVerb.POST, description = "delete anncmnt", produces = {
            MediaType.APPLICATION_JSON }, consumes = { MediaType.APPLICATION_JSON })
    void delete(@ApiBodyObject AdmAnncmntVo vo);

    @POST
    @Path("/findList/")
    @ApiMethod(path = "/api/rest/admAnncmnt/findList/", verb = ApiVerb.POST, description = "findList", produces = {
            MediaType.APPLICATION_JSON }, consumes = { MediaType.APPLICATION_JSON })
    List<AdmAnncmntVo> findList();

    @POST
    @Path("/findByTemplateName/")
    @ApiMethod(path = "/api/rest/admAnncmnt/findByTemplateName/", verb = ApiVerb.POST, description = "findByTemplateName", produces = {
            MediaType.APPLICATION_JSON }, consumes = { MediaType.APPLICATION_JSON })
    List<AdmAnncmntVo> findByTemplateName(
            @ApiQueryParam(name = "templateName", description = "template name") @QueryParam("templateName") String templateName);

    @GET
    @Path("getByTypeCd")
    @ApiMethod(path = "/api/rest/admAnncmnt/getByTypeCd/", verb = ApiVerb.GET, description = "get anncmnt by typeCd", produces = {
            MediaType.APPLICATION_JSON }, consumes = { MediaType.APPLICATION_JSON })
    @ApiResponseObject
    AdmAnncmntVo getByTypeCd(
            @ApiQueryParam(name = "typeCd", description = "The type cd") @QueryParam("typeCd") String typeCd);
}
