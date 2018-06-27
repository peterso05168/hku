package com.accentrix.hku.service.applicant;

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

import com.accentrix.hku.vo.applicant.AccountVo;

/**
 * @author Jaye.Lin
 * @date 创建时间：2018年1月31日 下午2:29:09
 * @version 1.0
 */

@Consumes({ MediaType.APPLICATION_JSON, MediaType.TEXT_XML })
@Produces({ MediaType.APPLICATION_JSON, MediaType.TEXT_XML })
@Api(name = "Account Service", description = "accountService")
public interface AccountService {

    @GET
    @Path("get")
    @ApiMethod(path = "/api/rest/account/get/", verb = ApiVerb.GET, description = "get account by id", produces = {
            MediaType.APPLICATION_JSON }, consumes = { MediaType.APPLICATION_JSON })
    @ApiResponseObject
    AccountVo get(@ApiQueryParam(name = "id", description = "The account id") @QueryParam("id") String id);

    @POST
    @Path("/")
    @ApiMethod(path = "/api/rest/account/", verb = ApiVerb.POST, description = "save account", produces = {
            MediaType.APPLICATION_JSON }, consumes = { MediaType.APPLICATION_JSON })
    @ApiResponseObject
    AccountVo save(@ApiBodyObject AccountVo vo);

    @POST
    @Path("/save-list/")
    @ApiMethod(path = "/api/rest/account/save-list/", verb = ApiVerb.POST, description = "save account list", produces = {
            MediaType.APPLICATION_JSON }, consumes = { MediaType.APPLICATION_JSON })
    @ApiResponseObject
    List<AccountVo> save(List<AccountVo> vos);

    @DELETE
    @Path("/{id}/")
    @ApiMethod(path = "/api/rest/account/{id}/", verb = ApiVerb.DELETE, description = "delete account by id", produces = {
            MediaType.APPLICATION_JSON }, consumes = { MediaType.APPLICATION_JSON })
    void delete(@ApiPathParam(name = "id", description = "The account Id") @PathParam("id") String id);

    @POST
    @Path("/delete-province/")
    @ApiMethod(path = "/api/rest/account/delete-account/", verb = ApiVerb.POST, description = "delete account", produces = {
            MediaType.APPLICATION_JSON }, consumes = { MediaType.APPLICATION_JSON })
    void delete(@ApiBodyObject AccountVo vo);

    @POST
    @Path("/findList/")
    @ApiMethod(path = "/api/rest/account/findList/", verb = ApiVerb.POST, description = "findList", produces = {
            MediaType.APPLICATION_JSON }, consumes = { MediaType.APPLICATION_JSON })
    List<AccountVo> findList();

    @POST
    @Path("/activationAccount/")
    @ApiMethod(path = "/api/rest/account/activationAccount/", verb = ApiVerb.POST, description = "activationAccount", produces = {
            MediaType.APPLICATION_JSON }, consumes = { MediaType.APPLICATION_JSON })
    boolean activationAccount(@ApiPathParam(name = "id") @QueryParam("id") String id);

    @POST
    @Path("/checkLogin/")
    @ApiMethod(path = "/api/rest/account/checkLogin/", verb = ApiVerb.POST, description = "checkLogin", produces = {
            MediaType.APPLICATION_JSON }, consumes = { MediaType.APPLICATION_JSON })
    AccountVo checkLogin(@ApiBodyObject AccountVo vo);

    @GET
    @Path("/checkEmail/")
    @ApiMethod(path = "/api/rest/account/checkEmail/", verb = ApiVerb.GET, description = "checkEmail", produces = {
            MediaType.APPLICATION_JSON }, consumes = { MediaType.APPLICATION_JSON })
    boolean checkEmail(@ApiPathParam(name = "email") @QueryParam("email") String email);

    @GET
    @Path("/checkEmailForGotPassword/")
    @ApiMethod(path = "/api/rest/account/checkEmailForGotPassword/", verb = ApiVerb.GET, description = "checkEmailForGotPassword", produces = {
            MediaType.APPLICATION_JSON }, consumes = { MediaType.APPLICATION_JSON })
    boolean checkEmailForGotPassword(@ApiPathParam(name = "email") @QueryParam("email") String email);

    @GET
    @Path("/checkResetPasswordEmail/")
    @ApiMethod(path = "/api/rest/account/checkResetPasswordEmail/", verb = ApiVerb.GET, description = "checkResetPasswordEmail", produces = {
            MediaType.APPLICATION_JSON }, consumes = { MediaType.APPLICATION_JSON })
    boolean checkResetPasswordEmail(@ApiPathParam(name = "email") @QueryParam("email") String email);

    @POST
    @Path("/resetPassword/")
    @ApiMethod(path = "/api/rest/account/resetPassword/", verb = ApiVerb.POST, description = "resetPassword", produces = {
            MediaType.APPLICATION_JSON }, consumes = { MediaType.APPLICATION_JSON })
    void resetPassword(@ApiPathParam(name = "email") @QueryParam("email") String email,
            @ApiPathParam(name = "password") @QueryParam("password") String password);

    @GET
    @Path("/getByApplicantInfoId/")
    @ApiMethod(path = "/api/rest/account/getByApplicantInfoId/", verb = ApiVerb.GET, description = "getByApplicantInfoId", produces = {
            MediaType.APPLICATION_JSON }, consumes = { MediaType.APPLICATION_JSON })
    AccountVo getByApplicantInfoId(
            @ApiPathParam(name = "applicantInfoId") @QueryParam("applicantInfoId") String applicantInfoId);
}
