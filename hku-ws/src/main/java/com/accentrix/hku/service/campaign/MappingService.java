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

import com.accentrix.hku.vo.campaign.MappingVo;

/** 
 * @author Jaye.Lin 
 * @date 创建时间：2018年4月9日 下午2:32:02 
 * @version 1.0 
 */

@Consumes({ MediaType.APPLICATION_JSON, MediaType.TEXT_XML })
@Produces({ MediaType.APPLICATION_JSON, MediaType.TEXT_XML })
@Api(name = "Mapping Service", description = "mappingService")
public interface MappingService {

    @GET
    @Path("get")
    @ApiMethod(path = "/api/rest/mapping/get/", verb = ApiVerb.GET, description = "get mapping by id", produces = {
            MediaType.APPLICATION_JSON }, consumes = { MediaType.APPLICATION_JSON })
    @ApiResponseObject
    MappingVo get(@ApiQueryParam(name = "id", description = "The mapping id") @QueryParam("id") String id);

    @POST
    @Path("/")
    @ApiMethod(path = "/api/rest/mapping/", verb = ApiVerb.POST, description = "save mapping", produces = {
            MediaType.APPLICATION_JSON }, consumes = { MediaType.APPLICATION_JSON })
    @ApiResponseObject
    MappingVo save(@ApiBodyObject MappingVo vo);

    @POST
    @Path("/save-list/")
    @ApiMethod(path = "/api/rest/mapping/save-list/", verb = ApiVerb.POST, description = "save mapping list", produces = {
            MediaType.APPLICATION_JSON }, consumes = { MediaType.APPLICATION_JSON })
    @ApiResponseObject
    List<MappingVo> save(@ApiBodyObject List<MappingVo> vos);

    @DELETE
    @Path("/{id}/")
    @ApiMethod(path = "/api/rest/mapping/{id}/", verb = ApiVerb.DELETE, description = "delete mapping by id", produces = {
            MediaType.APPLICATION_JSON }, consumes = { MediaType.APPLICATION_JSON })
    void delete(@ApiPathParam(name = "id", description = "The mapping Id") @PathParam("id") String id);

    @POST
    @Path("/delete-province/")
    @ApiMethod(path = "/api/rest/mapping/delete-mapping/", verb = ApiVerb.POST, description = "delete mapping", produces = {
            MediaType.APPLICATION_JSON }, consumes = { MediaType.APPLICATION_JSON })
    void delete(@ApiBodyObject MappingVo vo);

    @POST
    @Path("/findList/")
    @ApiMethod(path = "/api/rest/mapping/findList/", verb = ApiVerb.POST, description = "findList", produces = {
            MediaType.APPLICATION_JSON }, consumes = { MediaType.APPLICATION_JSON })
    List<MappingVo> findList();

    @POST
    @Path("/findMappingList/")
    @ApiMethod(path = "/api/rest/mapping/findMappingList/", verb = ApiVerb.POST, description = "findMappingList", produces = {
            MediaType.APPLICATION_JSON }, consumes = { MediaType.APPLICATION_JSON })
    List<MappingVo> findMappingList(
            @ApiQueryParam(name = "countryId", required = false) @QueryParam("countryId") String countryId,
            @ApiQueryParam(name = "provinceId", required = false) @QueryParam("provinceId") String provinceId);
}
