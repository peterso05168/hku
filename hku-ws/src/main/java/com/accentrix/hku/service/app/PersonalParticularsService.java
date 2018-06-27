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

import com.accentrix.hku.vo.app.PersonalParticularsVo;

/** 
 * @author Jaye.Lin 
 * @date 创建时间：2018年1月31日 下午2:22:51 
 * @version 1.0 
 */

@Consumes({ MediaType.APPLICATION_JSON, MediaType.TEXT_XML })
@Produces({ MediaType.APPLICATION_JSON, MediaType.TEXT_XML })
@Api(name = "Personal Particulars Service", description = "personalParticularsService")
public interface PersonalParticularsService {

    @GET
    @Path("get")
    @ApiMethod(path = "/api/rest/personalParticulars/get/", verb = ApiVerb.GET, description = "get personalParticulars by id", produces = {
            MediaType.APPLICATION_JSON }, consumes = { MediaType.APPLICATION_JSON })
    @ApiResponseObject
    PersonalParticularsVo get(
            @ApiQueryParam(name = "id", description = "The personalParticulars id") @QueryParam("id") String id);

    @POST
    @Path("/")
    @ApiMethod(path = "/api/rest/personalParticulars/", verb = ApiVerb.POST, description = "save personalParticulars", produces = {
            MediaType.APPLICATION_JSON }, consumes = { MediaType.APPLICATION_JSON })
    @ApiResponseObject
    PersonalParticularsVo save(@ApiBodyObject PersonalParticularsVo vo);

    @POST
    @Path("/save-list/")
    @ApiMethod(path = "/api/rest/personalParticulars/save-list/", verb = ApiVerb.POST, description = "save personalParticulars list", produces = {
            MediaType.APPLICATION_JSON }, consumes = { MediaType.APPLICATION_JSON })
    @ApiResponseObject
    List<PersonalParticularsVo> save(List<PersonalParticularsVo> vos);

    @DELETE
    @Path("/{id}/")
    @ApiMethod(path = "/api/rest/personalParticulars/{id}/", verb = ApiVerb.DELETE, description = "delete personalParticulars by id", produces = {
            MediaType.APPLICATION_JSON }, consumes = { MediaType.APPLICATION_JSON })
    void delete(@ApiPathParam(name = "id", description = "The personalParticulars Id") @PathParam("id") String id);

    @POST
    @Path("/delete-province/")
    @ApiMethod(path = "/api/rest/personalParticulars/delete-personalParticulars/", verb = ApiVerb.POST, description = "delete personalParticulars", produces = {
            MediaType.APPLICATION_JSON }, consumes = { MediaType.APPLICATION_JSON })
    void delete(@ApiBodyObject PersonalParticularsVo vo);

    @POST
    @Path("/findList/")
    @ApiMethod(path = "/api/rest/personalParticulars/findList/", verb = ApiVerb.POST, description = "findList", produces = {
            MediaType.APPLICATION_JSON }, consumes = { MediaType.APPLICATION_JSON })
    List<PersonalParticularsVo> findList();
}
