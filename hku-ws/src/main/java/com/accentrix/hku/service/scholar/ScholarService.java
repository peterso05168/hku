package com.accentrix.hku.service.scholar;

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

import com.accentrix.hku.vo.scholar.ScholarVo;

/**
 * @author Jaye.Lin
 * @date 创建时间：2018年3月19日 上午11:44:37
 * @version 1.0
 */

@Consumes({ MediaType.APPLICATION_JSON, MediaType.TEXT_XML })
@Produces({ MediaType.APPLICATION_JSON, MediaType.TEXT_XML })
@Api(name = "Scholar Service", description = "scholarService")
public interface ScholarService {

    @GET
    @Path("get")
    @ApiMethod(path = "/api/rest/scholar/get/", verb = ApiVerb.GET, description = "get scholar by id", produces = {
            MediaType.APPLICATION_JSON }, consumes = { MediaType.APPLICATION_JSON })
    @ApiResponseObject
    ScholarVo get(@ApiQueryParam(name = "id", description = "The scholar id") @QueryParam("id") String id);

    @POST
    @Path("/")
    @ApiMethod(path = "/api/rest/scholar/", verb = ApiVerb.POST, description = "save scholar", produces = {
            MediaType.APPLICATION_JSON }, consumes = { MediaType.APPLICATION_JSON })
    @ApiResponseObject
    ScholarVo save(@ApiBodyObject ScholarVo vo);

    @POST
    @Path("/save-list/")
    @ApiMethod(path = "/api/rest/scholar/save-list/", verb = ApiVerb.POST, description = "save scholar list", produces = {
            MediaType.APPLICATION_JSON }, consumes = { MediaType.APPLICATION_JSON })
    @ApiResponseObject
    List<ScholarVo> save(List<ScholarVo> vos);

    @DELETE
    @Path("/{id}/")
    @ApiMethod(path = "/api/rest/scholar/{id}/", verb = ApiVerb.DELETE, description = "delete scholar by id", produces = {
            MediaType.APPLICATION_JSON }, consumes = { MediaType.APPLICATION_JSON })
    void delete(@ApiPathParam(name = "id", description = "The scholar Id") @PathParam("id") String id);

    @POST
    @Path("/delete-province/")
    @ApiMethod(path = "/api/rest/scholar/delete-scholar/", verb = ApiVerb.POST, description = "delete scholar", produces = {
            MediaType.APPLICATION_JSON }, consumes = { MediaType.APPLICATION_JSON })
    void delete(@ApiBodyObject ScholarVo vo);

    @POST
    @Path("/findList/")
    @ApiMethod(path = "/api/rest/scholar/findList/", verb = ApiVerb.POST, description = "findList", produces = {
            MediaType.APPLICATION_JSON }, consumes = { MediaType.APPLICATION_JSON })
    List<ScholarVo> findList();

    @GET
    @Path("/getByName/")
    @ApiMethod(path = "/api/rest/scholar/getByName/", verb = ApiVerb.GET, description = "get scholar by get By Name", produces = {
            MediaType.APPLICATION_JSON }, consumes = { MediaType.APPLICATION_JSON })
    ScholarVo getByName(@ApiQueryParam(name = "name") @QueryParam("name") String name);

    @POST
    @Path("/basicSearch/")
    @ApiMethod(path = "/api/rest/scholar/basicSearch/", verb = ApiVerb.POST, description = "basicSearch", produces = {
            MediaType.APPLICATION_JSON }, consumes = { MediaType.APPLICATION_JSON })
    List<ScholarVo> basicSearch(@ApiQueryParam(name = "criteria") @QueryParam("criteria") String criteria);

    @POST
    @Path("/advancedSearch/")
    @ApiMethod(path = "/api/rest/scholar/advancedSearch/", verb = ApiVerb.POST, description = "advancedSearch", produces = {
            MediaType.APPLICATION_JSON }, consumes = { MediaType.APPLICATION_JSON })
    List<ScholarVo> advancedSearch(@ApiBodyObject ScholarVo searchVo);
}
