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

import org.jsondoc.core.annotation.Api;
import org.jsondoc.core.annotation.ApiBodyObject;
import org.jsondoc.core.annotation.ApiMethod;
import org.jsondoc.core.annotation.ApiPathParam;
import org.jsondoc.core.annotation.ApiQueryParam;
import org.jsondoc.core.annotation.ApiResponseObject;
import org.jsondoc.core.pojo.ApiVerb;

import com.accentrix.hku.vo.app.MfeSchemeVo;

/**
 * @author 作者lance.mao
 * @Email lance.mao@accentrix.com
 * @date 创建时间：2018年4月3日 下午6:46:15
 */
@Consumes({ MediaType.APPLICATION_JSON, MediaType.TEXT_XML })
@Produces({ MediaType.APPLICATION_JSON, MediaType.TEXT_XML })
@Api(name = "MfeScheme Service", description = "mfeSchemeService")
public interface MfeSchemeService {

    @GET
    @Path("get")
    @ApiMethod(path = "/api/rest/mfeScheme/get/", verb = ApiVerb.GET, description = "get mfeScheme by id", produces = {
            MediaType.APPLICATION_JSON }, consumes = { MediaType.APPLICATION_JSON })
    @ApiResponseObject
    MfeSchemeVo get(@ApiQueryParam(name = "id", description = "The mfeScheme id") @QueryParam("id") String id);

    @POST
    @Path("/")
    @ApiMethod(path = "/api/rest/mfeScheme/", verb = ApiVerb.POST, description = "save mfeScheme", produces = {
            MediaType.APPLICATION_JSON }, consumes = { MediaType.APPLICATION_JSON })
    @ApiResponseObject
    MfeSchemeVo save(@ApiBodyObject MfeSchemeVo vo);

    @POST
    @Path("/save-list/")
    @ApiMethod(path = "/api/rest/mfeScheme/save-list/", verb = ApiVerb.POST, description = "save mfeScheme list", produces = {
            MediaType.APPLICATION_JSON }, consumes = { MediaType.APPLICATION_JSON })
    @ApiResponseObject
    List<MfeSchemeVo> save(List<MfeSchemeVo> vos);

    @DELETE
    @Path("/{id}/")
    @ApiMethod(path = "/api/rest/mfeScheme/{id}/", verb = ApiVerb.DELETE, description = "delete mfeScheme by id", produces = {
            MediaType.APPLICATION_JSON }, consumes = { MediaType.APPLICATION_JSON })
    void delete(@ApiPathParam(name = "id", description = "The mfeScheme Id") @PathParam("id") String id);

    @POST
    @Path("/delete-province/")
    @ApiMethod(path = "/api/rest/mfeScheme/delete-mfeScheme/", verb = ApiVerb.POST, description = "delete mfeScheme", produces = {
            MediaType.APPLICATION_JSON }, consumes = { MediaType.APPLICATION_JSON })
    void delete(@ApiBodyObject MfeSchemeVo vo);

    @POST
    @Path("/findList/")
    @ApiMethod(path = "/api/rest/mfeScheme/findList/", verb = ApiVerb.POST, description = "findList", produces = {
            MediaType.APPLICATION_JSON }, consumes = { MediaType.APPLICATION_JSON })
    List<MfeSchemeVo> findList();

    @POST
    @Path("/getByQualificationIdAndYear/")
    @ApiMethod(path = "/api/rest/mfeScheme/getByQualificationIdAndYear/", verb = ApiVerb.POST, description = "getByQualificationIdAndYear", produces = {
            MediaType.APPLICATION_JSON }, consumes = { MediaType.APPLICATION_JSON })
    MfeSchemeVo getByQualificationIdAndYear(
            @ApiQueryParam(name = "qualificationId") @QueryParam("qualificationId") String qualificationId,
            @ApiQueryParam(name = "yearSemester") @QueryParam("yearSemester") Integer yearSemester);
}
