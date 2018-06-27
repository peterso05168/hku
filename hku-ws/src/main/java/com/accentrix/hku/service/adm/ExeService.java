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

import com.accentrix.hku.vo.adm.ExeVo;

/**
 * @author Jaye.Lin
 * @date 创建时间：2018年1月31日 下午2:17:40
 * @version 1.0
 */

@Consumes({ MediaType.APPLICATION_JSON, MediaType.TEXT_XML })
@Produces({ MediaType.APPLICATION_JSON, MediaType.TEXT_XML })
@Api(name = "Exe Service", description = "exeService")
public interface ExeService {

    @GET
    @Path("get")
    @ApiMethod(path = "/api/rest/exe/get/", verb = ApiVerb.GET, description = "get exe by id", produces = {
            MediaType.APPLICATION_JSON }, consumes = { MediaType.APPLICATION_JSON })
    @ApiResponseObject
    ExeVo get(@ApiQueryParam(name = "id", description = "The exe id") @QueryParam("id") String id);

    @POST
    @Path("/")
    @ApiMethod(path = "/api/rest/exe/", verb = ApiVerb.POST, description = "save exe", produces = {
            MediaType.APPLICATION_JSON }, consumes = { MediaType.APPLICATION_JSON })
    @ApiResponseObject
    ExeVo save(@ApiBodyObject ExeVo vo);

    @POST
    @Path("/save-list/")
    @ApiMethod(path = "/api/rest/exe/save-list/", verb = ApiVerb.POST, description = "save exe list", produces = {
            MediaType.APPLICATION_JSON }, consumes = { MediaType.APPLICATION_JSON })
    @ApiResponseObject
    List<ExeVo> save(List<ExeVo> vos);

    @DELETE
    @Path("/{id}/")
    @ApiMethod(path = "/api/rest/exe/{id}/", verb = ApiVerb.DELETE, description = "delete exe by id", produces = {
            MediaType.APPLICATION_JSON }, consumes = { MediaType.APPLICATION_JSON })
    void delete(@ApiPathParam(name = "id", description = "The exe Id") @PathParam("id") String id);

    @POST
    @Path("/delete-province/")
    @ApiMethod(path = "/api/rest/exe/delete-exe/", verb = ApiVerb.POST, description = "delete exe", produces = {
            MediaType.APPLICATION_JSON }, consumes = { MediaType.APPLICATION_JSON })
    void delete(@ApiBodyObject ExeVo vo);

    @POST
    @Path("/findList/")
    @ApiMethod(path = "/api/rest/exe/findList/", verb = ApiVerb.POST, description = "findList", produces = {
            MediaType.APPLICATION_JSON }, consumes = { MediaType.APPLICATION_JSON })
    List<ExeVo> findList();

    @GET
    @Path("/getByApplicationId/")
    @ApiMethod(path = "/api/rest/exe/getByApplicationId/", verb = ApiVerb.GET, description = "getByApplicationId", produces = {
            MediaType.APPLICATION_JSON }, consumes = { MediaType.APPLICATION_JSON })
    ExeVo getByApplicationId(@ApiQueryParam(name = "applicationId") @QueryParam("applicationId") String applicationId);

    @POST
    @Path("/findAdmissionYear/")
    @ApiMethod(path = "/api/rest/exe/findAdmissionYear/", verb = ApiVerb.POST, description = "findAdmissionYear", produces = {
            MediaType.APPLICATION_JSON }, consumes = { MediaType.APPLICATION_JSON })
    List<Integer> findAdmissionYear();
}
