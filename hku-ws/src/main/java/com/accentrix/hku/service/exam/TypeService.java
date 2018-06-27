package com.accentrix.hku.service.exam;

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

import com.accentrix.hku.vo.exam.TypeVo;

/** 
 * @author Jaye.Lin 
 * @date 创建时间：2018年1月31日 下午2:31:39 
 * @version 1.0 
 */

@Consumes({ MediaType.APPLICATION_JSON, MediaType.TEXT_XML })
@Produces({ MediaType.APPLICATION_JSON, MediaType.TEXT_XML })
@Api(name = "Type Service", description = "typeService")
public interface TypeService {

    @GET
    @Path("get")
    @ApiMethod(path = "/api/rest/type/get/", verb = ApiVerb.GET, description = "get type by id", produces = {
            MediaType.APPLICATION_JSON }, consumes = { MediaType.APPLICATION_JSON })
    @ApiResponseObject
    TypeVo get(@ApiQueryParam(name = "id", description = "The type id") @QueryParam("id") String id);

    @POST
    @Path("/")
    @ApiMethod(path = "/api/rest/type/", verb = ApiVerb.POST, description = "save type", produces = {
            MediaType.APPLICATION_JSON }, consumes = { MediaType.APPLICATION_JSON })
    @ApiResponseObject
    TypeVo save(@ApiBodyObject TypeVo vo);

    @POST
    @Path("/save-list/")
    @ApiMethod(path = "/api/rest/type/save-list/", verb = ApiVerb.POST, description = "save type list", produces = {
            MediaType.APPLICATION_JSON }, consumes = { MediaType.APPLICATION_JSON })
    @ApiResponseObject
    List<TypeVo> save(List<TypeVo> vos);

    @DELETE
    @Path("/{id}/")
    @ApiMethod(path = "/api/rest/type/{id}/", verb = ApiVerb.DELETE, description = "delete type by id", produces = {
            MediaType.APPLICATION_JSON }, consumes = { MediaType.APPLICATION_JSON })
    void delete(@ApiPathParam(name = "id", description = "The type Id") @PathParam("id") String id);

    @POST
    @Path("/delete-province/")
    @ApiMethod(path = "/api/rest/type/delete-type/", verb = ApiVerb.POST, description = "delete type", produces = {
            MediaType.APPLICATION_JSON }, consumes = { MediaType.APPLICATION_JSON })
    void delete(@ApiBodyObject TypeVo vo);

    @POST
    @Path("/findList/")
    @ApiMethod(path = "/api/rest/type/findList/", verb = ApiVerb.POST, description = "findList", produces = {
            MediaType.APPLICATION_JSON }, consumes = { MediaType.APPLICATION_JSON })
    List<TypeVo> findList();

    @POST
    @Path("/findByIdNotIn/")
    @ApiMethod(path = "/api/rest/type/findByIdNotIn/", verb = ApiVerb.POST, description = "find By Id Not In", produces = {
            MediaType.APPLICATION_JSON }, consumes = { MediaType.APPLICATION_JSON })
    List<TypeVo> findByIdNotIn(
            @ApiQueryParam(name = "ids", description = "The type ids") @QueryParam("ids") List<String> ids);

    @GET
    @Path("getByExamCd")
    @ApiMethod(path = "/api/rest/type/getByExamCd/", verb = ApiVerb.GET, description = "get type by exam cd", produces = {
            MediaType.APPLICATION_JSON }, consumes = { MediaType.APPLICATION_JSON })
    @ApiResponseObject
    TypeVo getByExamCd(
            @ApiQueryParam(name = "examCd", description = "The exam cd") @QueryParam("examCd") String examCd);
}
