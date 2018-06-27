package com.accentrix.hku.service.campaign;

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

import com.accentrix.hku.vo.campaign.CritNjToProgVo;

/** 
 * @author Jaye.Lin 
 * @date 创建时间：2018年4月9日 下午2:31:26 
 * @version 1.0 
 */

@Consumes({ MediaType.APPLICATION_JSON, MediaType.TEXT_XML })
@Produces({ MediaType.APPLICATION_JSON, MediaType.TEXT_XML })
@Api(name = "CritNjToProg Service", description = "critNjToProgService")
public interface CritNjToProgService {

    @GET
    @Path("get")
    @ApiMethod(path = "/api/rest/critNjToProg/get/", verb = ApiVerb.GET, description = "get critNjToProg by id", produces = {
            MediaType.APPLICATION_JSON }, consumes = { MediaType.APPLICATION_JSON })
    @ApiResponseObject
    CritNjToProgVo get(@ApiQueryParam(name = "id", description = "The critNjToProg id") @QueryParam("id") String id);

    @POST
    @Path("/")
    @ApiMethod(path = "/api/rest/critNjToProg/", verb = ApiVerb.POST, description = "save critNjToProg", produces = {
            MediaType.APPLICATION_JSON }, consumes = { MediaType.APPLICATION_JSON })
    @ApiResponseObject
    CritNjToProgVo save(@ApiBodyObject CritNjToProgVo vo);

    @POST
    @Path("/save-list/")
    @ApiMethod(path = "/api/rest/critNjToProg/save-list/", verb = ApiVerb.POST, description = "save critNjToProg list", produces = {
            MediaType.APPLICATION_JSON }, consumes = { MediaType.APPLICATION_JSON })
    @ApiResponseObject
    List<CritNjToProgVo> save(@ApiBodyObject List<CritNjToProgVo> vos);

    @DELETE
    @Path("/{id}/")
    @ApiMethod(path = "/api/rest/critNjToProg/{id}/", verb = ApiVerb.DELETE, description = "delete critNjToProg by id", produces = {
            MediaType.APPLICATION_JSON }, consumes = { MediaType.APPLICATION_JSON })
    void delete(@ApiPathParam(name = "id", description = "The critNjToProg Id") @PathParam("id") String id);

    @POST
    @Path("/delete-province/")
    @ApiMethod(path = "/api/rest/critNjToProg/delete-critNjToProg/", verb = ApiVerb.POST, description = "delete critNjToProg", produces = {
            MediaType.APPLICATION_JSON }, consumes = { MediaType.APPLICATION_JSON })
    void delete(@ApiBodyObject CritNjToProgVo vo);

    @POST
    @Path("/findList/")
    @ApiMethod(path = "/api/rest/critNjToProg/findList/", verb = ApiVerb.POST, description = "findList", produces = {
            MediaType.APPLICATION_JSON }, consumes = { MediaType.APPLICATION_JSON })
    List<CritNjToProgVo> findList();

    @POST
    @Path("/findByCritNjId/")
    @ApiMethod(path = "/api/rest/critNjToProg/findByCritNjId/", verb = ApiVerb.POST, description = "findByCritNjId", produces = {
            MediaType.APPLICATION_JSON }, consumes = { MediaType.APPLICATION_JSON })
    List<String> findByCritNjId(
            @ApiQueryParam(name = "critNjId", required = false) @QueryParam("critNjId") String critNjId);

    @POST
    @Path("/findByCritNjIdToList/")
    @ApiMethod(path = "/api/rest/critNjToProg/findByCritNjIdToList/", verb = ApiVerb.POST, description = "findByCritNjIdToList", produces = {
            MediaType.APPLICATION_JSON }, consumes = { MediaType.APPLICATION_JSON })
    List<CritNjToProgVo> findByCritNjIdToList(
            @ApiQueryParam(name = "critNjId", required = false) @QueryParam("critNjId") String critNjId);
}
