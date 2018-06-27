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

import com.accentrix.hku.vo.campaign.CentreVo;

/** 
 * @author Jaye.Lin 
 * @date 创建时间：2018年4月9日 下午2:29:39 
 * @version 1.0 
 */

@Consumes({ MediaType.APPLICATION_JSON, MediaType.TEXT_XML })
@Produces({ MediaType.APPLICATION_JSON, MediaType.TEXT_XML })
@Api(name = "Centre Service", description = "centreService")
public interface CentreService {

    @GET
    @Path("get")
    @ApiMethod(path = "/api/rest/centre/get/", verb = ApiVerb.GET, description = "get centre by id", produces = {
            MediaType.APPLICATION_JSON }, consumes = { MediaType.APPLICATION_JSON })
    @ApiResponseObject
    CentreVo get(@ApiQueryParam(name = "id", description = "The centre id") @QueryParam("id") String id);

    @POST
    @Path("/")
    @ApiMethod(path = "/api/rest/centre/", verb = ApiVerb.POST, description = "save centre", produces = {
            MediaType.APPLICATION_JSON }, consumes = { MediaType.APPLICATION_JSON })
    @ApiResponseObject
    CentreVo save(@ApiBodyObject CentreVo vo);

    @POST
    @Path("/save-list/")
    @ApiMethod(path = "/api/rest/centre/save-list/", verb = ApiVerb.POST, description = "save centre list", produces = {
            MediaType.APPLICATION_JSON }, consumes = { MediaType.APPLICATION_JSON })
    @ApiResponseObject
    List<CentreVo> save(@ApiBodyObject List<CentreVo> vos);

    @DELETE
    @Path("/{id}/")
    @ApiMethod(path = "/api/rest/centre/{id}/", verb = ApiVerb.DELETE, description = "delete centre by id", produces = {
            MediaType.APPLICATION_JSON }, consumes = { MediaType.APPLICATION_JSON })
    void delete(@ApiPathParam(name = "id", description = "The centre Id") @PathParam("id") String id);

    @POST
    @Path("/delete-province/")
    @ApiMethod(path = "/api/rest/centre/delete-centre/", verb = ApiVerb.POST, description = "delete centre", produces = {
            MediaType.APPLICATION_JSON }, consumes = { MediaType.APPLICATION_JSON })
    void delete(@ApiBodyObject CentreVo vo);

    @POST
    @Path("/findList/")
    @ApiMethod(path = "/api/rest/centre/findList/", verb = ApiVerb.POST, description = "findList", produces = {
            MediaType.APPLICATION_JSON }, consumes = { MediaType.APPLICATION_JSON })
    List<CentreVo> findList();

    @POST
    @Path("/findByCpgnId/")
    @ApiMethod(path = "/api/rest/centre/findByCpgnId/", verb = ApiVerb.POST, description = "findByCpgnId", produces = {
            MediaType.APPLICATION_JSON }, consumes = { MediaType.APPLICATION_JSON })
    List<CentreVo> findByCpgnId(@ApiQueryParam(name = "cpgnId", required = false) @QueryParam("cpgnId") String cpgnId);

    @POST
    @Path("/findByCpgnIdAndProvinceOrCity/")
    @ApiMethod(path = "/api/rest/centre/findByCpgnIdAndProvinceOrCity/", verb = ApiVerb.POST, description = "findByCpgnIdAndProvinceOrCity", produces = {
            MediaType.APPLICATION_JSON }, consumes = { MediaType.APPLICATION_JSON })
    List<CentreVo> findByCpgnIdAndProvinceOrCity(
            @ApiQueryParam(name = "cpgnId", required = false) @QueryParam("cpgnId") String cpgnId,
            @ApiQueryParam(name = "type", required = false) @QueryParam("type") String type);

    @POST
    @Path("/findByType/")
    @ApiMethod(path = "/api/rest/centre/findByType/", verb = ApiVerb.POST, description = "findByType", produces = {
            MediaType.APPLICATION_JSON }, consumes = { MediaType.APPLICATION_JSON })
    List<CentreVo> findByType(@ApiQueryParam(name = "type", required = false) @QueryParam("type") String type,
            @ApiQueryParam(name = "provinceOrCity", required = false) @QueryParam("provinceOrCity") String provinceOrCity,
            @ApiQueryParam(name = "cpgnId", required = false) @QueryParam("cpgnId") String cpgnId);

    @POST
    @Path("/findByIds/")
    @ApiMethod(path = "/api/rest/centre/findByIds/", verb = ApiVerb.POST, description = "findByIds", produces = {
            MediaType.APPLICATION_JSON }, consumes = { MediaType.APPLICATION_JSON })
    List<CentreVo> findByIds(@ApiQueryParam(name = "ids", required = false) @QueryParam("ids") List<String> ids,
            @ApiQueryParam(name = "type", required = false) @QueryParam("type") String type,
            @ApiQueryParam(name = "provinceOrCity", required = false) @QueryParam("provinceOrCity") String provinceOrCity,
            @ApiQueryParam(name = "cpgnId", required = false) @QueryParam("cpgnId") String cpgnId);

    @POST
    @Path("/findByIdList/")
    @ApiMethod(path = "/api/rest/centre/findByIdList/", verb = ApiVerb.POST, description = "findByIdList", produces = {
            MediaType.APPLICATION_JSON }, consumes = { MediaType.APPLICATION_JSON })
    List<CentreVo> findByIdList(@ApiQueryParam(name = "ids", required = false) @QueryParam("ids") List<String> ids);
}
