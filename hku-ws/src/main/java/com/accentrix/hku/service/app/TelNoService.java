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

import com.accentrix.hku.vo.app.TelNoVo;

/** 
 * @author Jaye.Lin 
 * @date 创建时间：2018年1月31日 下午2:27:58 
 * @version 1.0 
 */

@Consumes({ MediaType.APPLICATION_JSON, MediaType.TEXT_XML })
@Produces({ MediaType.APPLICATION_JSON, MediaType.TEXT_XML })
@Api(name = "TelNo Service", description = "telNoService")
public interface TelNoService {

    @GET
    @Path("get")
    @ApiMethod(path = "/api/rest/telNo/get/", verb = ApiVerb.GET, description = "get telNo by id", produces = {
            MediaType.APPLICATION_JSON }, consumes = { MediaType.APPLICATION_JSON })
    @ApiResponseObject
    TelNoVo get(@ApiQueryParam(name = "id", description = "The telNo id") @QueryParam("id") String id);

    @POST
    @Path("/")
    @ApiMethod(path = "/api/rest/telNo/", verb = ApiVerb.POST, description = "save telNo", produces = {
            MediaType.APPLICATION_JSON }, consumes = { MediaType.APPLICATION_JSON })
    TelNoVo save(@ApiBodyObject TelNoVo vo);

    @POST
    @Path("/save-list/")
    @ApiMethod(path = "/api/rest/telNo/save-list/", verb = ApiVerb.POST, description = "save telNo list", produces = {
            MediaType.APPLICATION_JSON }, consumes = { MediaType.APPLICATION_JSON })
    @ApiResponseObject
    List<TelNoVo> save(List<TelNoVo> vos);

    @DELETE
    @Path("/{id}/")
    @ApiMethod(path = "/api/rest/telNo/{id}/", verb = ApiVerb.DELETE, description = "delete telNo by id", produces = {
            MediaType.APPLICATION_JSON }, consumes = { MediaType.APPLICATION_JSON })
    void delete(@ApiPathParam(name = "id", description = "The telNo Id") @PathParam("id") String id);

    @POST
    @Path("/delete-province/")
    @ApiMethod(path = "/api/rest/telNo/delete-telNo/", verb = ApiVerb.POST, description = "delete telNo", produces = {
            MediaType.APPLICATION_JSON }, consumes = { MediaType.APPLICATION_JSON })
    void delete(@ApiBodyObject TelNoVo vo);

    @POST
    @Path("/findList/")
    @ApiMethod(path = "/api/rest/telNo/findList/", verb = ApiVerb.POST, description = "findList", produces = {
            MediaType.APPLICATION_JSON }, consumes = { MediaType.APPLICATION_JSON })
    List<TelNoVo> findList();
}
