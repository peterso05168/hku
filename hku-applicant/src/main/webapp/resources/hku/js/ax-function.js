function _randId(_x){  
	var _xP = 'ABC';  
	var _xS = 'XYZ';  
	return _xP + _x + Math.floor((Math.random() * 900000) + 1) + _xS;  
}  
function _winOpen(_url) {  
    window.open(_url, "_blank", "fullscreen=no,titlebar=no,status=no,menubar=no,location=no,toolbar=no,scrollbars=yes,resizable=yes,top=0,left=0,width=1003,height=768");  
}  
function _randId(_x){  
	var _xP = 'ABC';  
	var _xS = 'XYZ';  
	return _xP + _x + Math.floor((Math.random() * 900000) + 1) + _xS;  
}   
function _BCTION(_type){  
	switch (_type) {  
		case 'student-list':  
			// window.location = "#/cs-student-result-01";			  
			_popup('popup.html#/ad-cs-setting-student-list', {size:54,title:'Student List',iframe:'true'});  
			break;		  
		case 'app-edit':  
			window.location='ad-main.html#/ad-app-edit';  
			break;		  
		case 'app-view':  
			window.location='ad-main.html#/ad-app-edit';  
			break;		  
		case 'change-status':  
			_popup('popup.html#/popup-change-status', {size:31,title:'Change Status',iframe:'true'});  
			break;		  
		case 'assign-scholarship-02':  
			_popup('popup.html#/popup-assign-scholarship-02', {size:54,title:'Assign Scholarship',iframe:'true'});  
			break;		  
		case 'assign-scholarship':  
			_popup('popup.html#/popup-assign-scholarship', {size:54,title:'Assign Scholarship',iframe:'true'});  
			break;		  
		case 'assign-offer':  
			_popup('popup.html#/popup-assign-offer', {size:54,title:'Assign Offer',iframe:'true'});  
			break;		  
		case 'firm-offer':  
			_popup('popup.html#/popup-document-list-', {size:54,title:'Assign Offer',iframe:'true'});  
			break;		  
		case 'cond-offer':  
			_popup('popup.html#/popup-document-list-', {size:54,title:'Assign Offer',iframe:'true'});  
			break;		  
		case 'assign-interview':  
			_popup('popup.html#/popup-assign-interview', {size:54,title:'Assign Interview',iframe:'true'});  
			break;		  
		case 'document-list':  
			_popup('popup.html#/popup-document-list', {size:54,title:'Supporting Document(s)',iframe:'true'});  
			break;		  
		case 'curriculum-edit':  
			_popup('popup.html#/popup-curriculum-edit', {size:54,title:'Choice of Programme',iframe:'true'});  
			break;  
		case 'qualification-edit':  
			_popup('popup.html#/popup-qualification-edit', {size:54,title:'Academic Qualifications',iframe:'true'});  
			break;  
		case 'previous-study-edit':  
			_popup('popup.html#/popup-previous-study-edit', {size:54,title:'Previous Studies',iframe:'true'});  
			break;			  
		case 'achievement-edit':  
			_popup('popup.html#/popup-achievement-edit', {size:53,title:'Extra-curricular Achievements',iframe:'true'});  
			break;		  
		case 'referee-edit':  
			_popup('popup.html#/popup-referee-edit', {size:22,title:'Referee',iframe:'true'});  
			break;					  
		case 'document-edit':  
			window.top._popup('popup.html#/popup-document-edit', {size:42,title:'Supporting Documents',iframe:'true'});  
			break;   
		case 'document-add':  
			window.top._popup('popup.html#/popup-document-add', {size:22,title:'Add Supporting Document',iframe:'true'});  
			break;   
		case 'subject-edit-02':  
			_popup('popup.html#/popup-subject-02-edit', {size:32,title:'Subject Requirement',iframe:'true'});  
			break;   
		case 'examination-edit':  
			_popup('popup.html#/popup-examination-edit', {size:24,title:'Examination',iframe:'true'});  
			break;   
		case 'requirement-edit':  
			_popup('popup.html#/popup-requirement-edit', {size:22,title:'Requirement',iframe:'true'});  
			break;  
		case 'requirement-edit-02':  
			_popup('popup.html#/popup-requirement-edit-02', {size:22,title:'Requirement',iframe:'true'});  
			break;			  
		case 'download':  
			_downloadFile(1);  
			break;  
		case 'doc_view':  
			_downloadFile(1);  
			break;				  
		default:  
			console.log('default breadcrumb.html');  
	}	  
}  
  
function _mAction(_type, _e){  
	if(_type == 'logout'){  
		window.location = '../index/login.htm';		  
	}else if(_type == 'pu-application'){ 		  
		_setDummyStatus('_logined', 'true');  
		window.location='pu-main.html#/pu-myapp-edit';  
	}else if(_type == 'pu-declare'){ 			  
		_setDummyStatus('_logined', 'false');  
		window.location='pu-main-02.html#/pu-declare';  
	}else if(_type == 'pu-register'){ 			  
		_setDummyStatus('_logined', 'false');  
		window.location='pu-main-02.html#/pu-register';  
	}else if(_type == 'pu-login'){ 	  
		_setDummyStatus('_logined', 'true');  
		window.location='pu-main.html#/pu-inbox';  
	}else if(_type == 'pu-logout'){ 	  
		_setDummyStatus('_logined', 'false');  
		window.location='pu-main-02.html#/pu-logout-02';		  
	  
	}else if(_type == 'cs-login'){ 	  
		_setDummyStatus('_logined', 'true');  
		window.location='cs-main.html#/cs-student-result';  
	}else if(_type == 'cs-logout'){ 	  
		_setDummyStatus('_logined', 'false');  
		window.location='cs-main-02.html#/cs-logout-02';		  
  
	}else if(_type == 'lib'){   
		window.location = '../lib/index.htm';  
	}else if(_type == 'home'){   
		window.location = '../index/dashboard.htm';  
	}else if(_type == 'pass-connect'){   
		window.location = '../index/pass-connect.htm';  
	}else if(_type == 'filter'){   
	}else if(_type == 'ContractA' || _type == 'ContractB'){   
		window.location = '../index/contract.htm';  
	}else{	   
		window.location = _type;  
	}  
}  
function _hightLightLeftMenu(_id){  
	$('#'+_id).addClass('selected');  
}  
function _backAction(_type){	  
	if(_type != undefined){  
		window.location = _type;  
	}else{  
		window.history.back();	  
	}	  
}  
function _moveUpAction(_e){  
	// for prototype only  
	var _this = $(_e.currentTarget);  
	var _tr = findTr(_this);	  
	_tr.insertBefore(_tr.prev());  
}  
function _moveDownAction(_e){  
	// for prototype only  
	var _this = $(_e.currentTarget);  
	var _tr = findTr(_this);  
	 _tr.insertAfter(_tr.next());  
}  
function findTr(_this){  
	// for prototype only  
	if(_this.parent().prop('tagName') == 'LI'){		  
		var _div = _this.parentsUntil('div').last().parent();  
		var _button = $('#'+_div.prop('id').replace('_menu', '_button'));  
		var _tr = _button.parentsUntil('tr').last().parent();  
	}else{  
		var _tr = _this.parentsUntil('tr').last().parent();  
	}  
	return _tr;  
}  
function _deleteAction(_e){  
	// for prototype only  
	var _this = $(_e.currentTarget);  
	if(confirm('Confirm to delete the selected record?')){		   
		var _tr = findTr(_this);  
		_tr.addClass('ar-hide');  
	}  
}  
function _inactiveAction(_e){  
	// for prototype only  
	var _this = $(_e.currentTarget);  
	if(confirm('Confirm to inactive the selected record?')){		   
//		var _tr = findTr(_this);  
		//_tr.addClass('ar-hide');  
	}  
}  
function _removeAction(_msg, _e){  
	// for prototype only  
	var _this = $(_e.currentTarget);  
	if(confirm(_msg)){		   
		// var _tr = findTr(_this);  
		// _tr.addClass('ar-hide');  
	}  
}  
function _highLightLeftMenu(_id){  
	$(document.body).ready(function(e) {		  
		$('.menu > ul > ' + _id).addClass('selected');          
    });  
}  
function _downloadFile(num){	  
	if(num == 1){  
		if(confirm('Start downloading the PDF file.')){  
			window.open("doc/doc_dummy.pdf", '_blank');  
		}  
	}else if (num == 2){  
		if(confirm('Start downloading the Excel file.')){  
			window.open("doc/doc_dummy.xls", '_blank');  
		}  
	}else if (num == 99){  
		if(confirm('Start downloading the Question Template')){  
			window.open("doc/question_template.xls", '_blank');  
		}  
	}else{  
		window.open("doc/doc_dummy.pdf", '_blank');  
	}  
}  
function _mACTION(_type){  
	switch (_type) {  
		case 'notice':  
			_popup('popup.html#/ad-notice-edit', {size:33,title:'Notification',iframe:'true'});  
			break;  
		case 'doc_view':  
			_downloadFile(1);  
			break;				  
		default:  
			//console.log('default breadcrumb.html');  
	}	  
}  
function _initCssMenu(){  
	$('.css-menu').each(function(_x) {	  
		var _a = $(this).find('ul').siblings('a');   
		_a.attr('href', 'javascript:void(0)'); 		  
		// block url if it has child (ul)  
		$(_a).each(function(_y) {  
			var _clickCheckbox = $('<input/>')  
				.addClass('click-checkbox')  
				.addClass('hidden-checkbox')  
				.prop('type', 'checkbox')  
				.prop('checked', false)  
				.prop('id', '-'+_x+_y+'-');  
			var _clickLabel = $('<label></label>')  
				.addClass('click-label')  
				.prop('for', '-'+_x+_y+'-');  
			var _arrow = $('<span></span>')  
				.addClass('arrow')  
				.addClass('ti-angle-right');			  
			$(this).before(_clickCheckbox).before(_clickLabel).before(_arrow);  
			_clickLabel.css('height', $(this).css('height'));  
		});  
		var _urls = $(this).find('a');		  
		_urls.each(function() {  
			$(this).click(function(){				  
				$('#rwd-menu-cbx-'+_x+'-').click();  
			});  
		});  
	});  
	$('.css-menu.rwd').each(function(_x) {	  
		// responsive web design menu   
		// add [三]  
		var _trigger = $('<label></label>')  
			.addClass('rwd-menu-trigger')  
			.prop('id', 'rwd-menu-trigger-'+_x+'-')  
			.prop('for', 'rwd-menu-cbx-'+_x+'-')  
			.html('<i class="ti-menu"></i>');  
		var _cbx = $('<input/>')  
			.addClass('rwd-menu-cbx')  
			.addClass('hidden-checkbox')   
			.prop('type', 'checkbox')			  
			.prop('id', 'rwd-menu-cbx-'+_x+'-')  
			.prop('checked', false)  
			.html('<i class="ti-menu"></i>');  
		var _mask = $('<div></div>')  
			.addClass('rwd-menu-mask')  
			.prop('id', 'rwd-menu-mask-'+_x+'-');			  
		_cbx.click(function(e) {  
			//console.log('rwd-menu-cbx.click');  
			if($(this).prop('checked')){  
				//console.log('blocked-body1');			  
				$('body').addClass('blocked-body').removeClass('default-body');			  
			}else{  
				//console.log('default-body2');			  
				$('body').addClass('default-body').removeClass('blocked-body');				  
			}	  
		});				  
		_mask.click(function(e) {  
			//console.log('rwd-menu-mask.click');		  
			$('#rwd-menu-cbx-'+_x+'-').click();  
		});				  
		$(this).before(_trigger).before(_cbx).before(_mask);		  
		if(_x > 0){  
			$('#rwd-menu-trigger-'+_x+'-').css('left',   
				parseInt($('#rwd-menu-trigger-'+eval(_x-1)+'-').css('left')) +   
				parseInt($('#rwd-menu-trigger-'+eval(_x-1)+'-').css('width')) +   
				20  
			);   
		}  
	});   
	$('body').addClass('default-body').removeClass('blocked-body');  
}  
  
function _initBootstrap(){  
//	alert('_initBootstrap');  
	$('[data-toggle="tooltip"]').tooltip();  
//	$('.dropdown-toggle').dropdown();	  
}  
  
function _initBlockUI(_label, _type, _callback){	  
	if(typeof _type != 'string'){  
		_callback = _type;		  
		_type = 'type-a';  
	}   
	$(document.body).prepend('<div class="ax-block-ui ' + _type + '" >' +   
		'<div class="label-text" >'+_label+'</div>' +  
		'<div class="block-ui-close" onclick="_closePreviewBlockUI()" ><i class="ti ti-close"></i></div>' +   
	'</div>');  
	$('.block-ui-close, .label-text').click(function(e) {  
	   _callback();  
    });  
}  
  
  
function _AnswerTypeOnChange(_this){  
	//alert($(_this).val());  
	var _value = $(_this).val();  
	var _answers = $('.ui-answers');  
	_answers.find('ul').hide();  
	_answers.find('ul.'+_value).show();  
}  
  
function _surveyFormInit(){	  
	function setCollapsed(v1, v2, v3){  
		$('.ui-survey div.ui-sec').attr('data-collapsed', (v1==1?'true':'false') );	  
		$('.ui-survey div.ui-no').attr('data-collapsed', (v2==1?'true':'false') );	  
		$('.ui-survey div.ui-qtn').attr('data-collapsed', (v3==1?'true':'false') );	  
	}  
	var _triggerReg = $('.ui-survey div.ui-sec, .ui-survey div.ui-qtn, .ui-survey div.ui-no');	  
	_triggerReg.each(function(){  
		if($(this).attr('data-collapsed') == undefined){  
			$(this).attr('data-collapsed', 'false');  
		}	  
	});  
	_triggerReg.click(function(){  
		$(this).attr('data-collapsed', ($(this).attr('data-collapsed') == 'true' ? 'false' : 'true') );		  
	});  
	/*  
	var _controlBtns = $('.ui-survey div.control-btns i.icon');		  
	_controlBtns.each(function(index){		  
		$(this).click(function(){  
			switch(index) {  
				case 0:  
					setCollapsed(1, 1, 1);  
					break;  
				case 1:  
					setCollapsed(0, 1, 1);  
					break;  
				case 2:  
					setCollapsed(0, 0, 1);  
					break;  
				case 3:  
					setCollapsed(0, 0, 0);  
					break;  
				default:  
					setCollapsed(0, 0, 0);  
			}   
		});  
	});	  
	*/  
	var _qtns = $('.ui-survey ul.ui-qtns > li');  
	$('.control-btns').append('<select class="inputSelects" id="qtn-select" onchange="qtnSelectOnChange(this)" >'+  
							  '<option value="all"> - All - </option>'+							    
							  '<option value="qtn"> - Show Question(s) - </option>'+  
							  '<option value="num"> - Show Question No. - </option>'+  
							  '<option value="sec"> - Show Section(s) - </option>'+  
							  '</select>');  
	_qtns.each(function(index){  
		var _i = index + 1;  
		//$('.control-btns').append('<div class="page-btn btn-'+_i+'" >'+_i+'</div>');  
		$('#qtn-select').append('<option value="'+index+'" >'+_qtns.eq(index).find('.ui-no').html()+'</option>');  
	});  
	$('#qtn-select').change(function(){		  
		var _val = $(this).val();  
		if(_val == 'all'){  
			setCollapsed(0, 0, 0);  
		}else if(_val == 'sec'){  
			setCollapsed(1, 1, 1);  
		}else if(_val == 'num'){  
			setCollapsed(0, 1, 1);  
		}else if(_val == 'qtn'){  
			setCollapsed(0, 0, 1);  
		}else{  
			setCollapsed(1, 1, 1);  
			var _section = _qtns.eq(_val).parent()  
			.parent().find('div.ui-sec').attr('data-collapsed', 'false');			  
			_qtns.eq(_val).find('.ui-no').attr('data-collapsed', 'false');  
			_qtns.eq(_val).find('.ui-qtn').attr('data-collapsed', 'false');	  
		}  
	});  
	var _pageBtns = $('.control-btns div.page-btn');	  
	/*  
	_pageBtns.each(function(index){  
		var _i = index + 1;  
		$(this).click(function(){  
			setCollapsed(1, 1, 1);  
			var _section = _qtns.eq(index).parent()  
			.parent().find('div.ui-sec').attr('data-collapsed', 'false');			  
			_qtns.eq(index).find('.ui-no').attr('data-collapsed', 'false');  
			_qtns.eq(index).find('.ui-qtn').attr('data-collapsed', 'false');			  
		});	  
	});  
	*/  
}  
function _addAns(_e){  
	//for prototype only  
	var _this = $(_e.currentTarget);  
	var _li = _this.parent();	  
	_li.clone().insertAfter(_li);  
	_li.next().find('.inputText').eq(0).val('');  
}  
function _removeAns(_e){  
	//for prototype only  
	var _this = $(_e.currentTarget);  
	var _li = _this.parent();  
	var _ul = _this.parent().parent();  
	if(_ul.find('li').length > 1){  
		_li.remove();  
	}	  
}   
function _initAxToolTips(){	  
	$('.ax-tooltip').each(function(_x) {  
		if($(this).attr('transformed') == undefined){  
			_x = _randId(_x);  
			var _icon = $(this).find('.icon').eq(0);   
			_icon.prop('id', 'tooltip-icon'+_x);  
			var _content = $(this).find('.content').eq(0);   
			_content.prop('id', 'tooltip-content'+_x);  
			$(this).attr('transformed', 'true');  
			_axPopover({title:'', trigger:'tooltip-icon'+_x, content:'tooltip-content'+_x});		  
		}  
	});		  
}  
function _axPopover(_pmrObj){  
	var _title = _pmrObj.title;  
	var _triggerID = _pmrObj.trigger;  
	var _contentID = _pmrObj.content;  
	var _target = (_triggerID == undefined ? '[data-toggle="popover"]' : '#'+_triggerID);	  
	$(_target).popover(  
		{  
			html: true,  
			container: 'body',  
			placement: 'auto left',  
			title: (_title == undefined ? '' : _title),  
			delay: { "show": 80, "hide": 80 },  
			template: '<div class="popover popover-medium ax-popover" ><div class="arrow"></div><div class="popover-inner" ><h3 class="popover-title"></h3><div class="popover-content"><p></p></div></div></div>',  
			content:  function () {  
				return $("#"+_contentID).html();  
			}  
		}  
	)	  
} 		  
function _setStep(_id, _num){  
	var _num = _num - 1;  
	var _st = $('#'+_id).find('.step');  
	_st.removeClass('selected');  
	_st.eq(_num).addClass('selected');  
}  
  
$(document.body).ready(function(e) {	  
	$(this).mousedown(function(e) {  
		if (e.ctrlKey) {  
			if(e.which == 3){				   
				window.location.reload();  
			}  
		}  
		if (e.altKey) {  
			if(e.which == 3){  
				// for _ngIncludePath -------------------  
				var _tHref = document.location.toString();  
				var _tAry = _tHref.split('/');  
				var _tA = _tAry[_tAry.length - 2];  
					_tHref = _tHref.replace(_tA, 'pages') + '.html';			  
				_ngPathDummyCapture(_tHref);  
				// for _ngIncludePath -------------------				  
			}  
		}  
	});	 		  
	//alert($('[data-toggle="popover"]').length);  
	//$('[data-toggle="popover"]').popover({html: true});  
	//$('[data-toggle="tooltip"]').tooltip();  
	  
	if(_ws.get('_previewMode') == 'true'){	  
//		_initBlockUI('Close Preview', 'type-a', function(){ window.close();	_ws.remove('_previewMode');});	  
	}  
	//_initBootstrap();  
	//$('[data-toggle="tooltip"]').tooltip();  
	//$('.dropdown-toggle').dropdown();	  
	  
	/* --------------------------------------------------------- */  
	/* ------ for prototype only, remove this at DEV ----------- */  
	/* --------------------------------------------------------- */  
	/*  
	$(this).mousedown(function(e) {  
		if (e.ctrlKey) {  
			if(e.which == 1){								  
				$("body").click(function(event) {  
					var _curTarget = $(event.target).parents('section.ng-scope').eq(0);  
					var _tgt = _curTarget.find('.ng-path-dummy').eq(0);  
						_tgt.css('border', '10px solid red');  
				});  
			}  
		}  
	});	  
	//tommy  
	*/  
	$(this).keyup(function(e) {  
		var _num = 48;  
		if (e.altKey) {			  
			if(e.which >= 48 && e.which <= 59 ){ // alt 1-9  
				_num = e.which - 48;				  
				if(_num == 1){  
					$('.selectors').toggleClass('ar-hide');					  
					_ws.set('selectorShow', !$('.selectors').hasClass('ar-hide'));   
				}else if(_num === 2){					  
					_changeView('printPreview');  
				}else if(_num === 3){  
					_changeView('readOnly');  
				}else if(_num === 4){					  
					$('#layout-mainLayout').click();				  
				}else if(_num === 5){  
					_menuExpandAllToggle();  
				}else if(_num == 0){					  
					_ngIncludePathShow();  
				}  
			}  
		}		  
	});		  
	//_initThemeSwitcher();  
	//_surveyFormInit();	  
	//_initDummyStatus();	  
	//_ngIncludePath();  
	_initAxToolTips();   
	_initBasicAdvance();  
	/* --------------------------------------------------------- */  
	/* ------ for prototype only, remove this at DEV ----------- */  
	/* --------------------------------------------------------- */  
});  
  
function _ngPathDummyCapture(_tHref){  
	var _divX = $('<input />')  
		.prop('type', 'text')  
		.prop('id', 'ng-path-dummy-capture')  
		.prop('value', _tHref)  
		.addClass('ng-path-dummy-capture');  
	if($('.ng-path-dummy-capture').length == 0){  
		$(document.body).prepend(_divX);	  
	}else{  
		$('#ng-path-dummy-capture').val(_tHref);  
	}				  
	$('#ng-path-dummy-capture').select();  
	document.execCommand("copy");	  
}  
  
function _menuExpandAllToggle(){  
	var _tmp = $('.tx-menu > ul');  
	var _isChecked = _tmp.find('input[type=checkbox]').eq(0).prop('checked');					  
	_tmp.find('input[type=checkbox]').prop('checked', _isChecked).click();					  
	document.querySelector('.menu-layout .tx-menu > ul').scrollTop = 0;  
}  
  
var _ws = {  
	set : function(_name, _value){  
		if(typeof(Storage) !== "undefined") {		  
			sessionStorage.setItem(_name, _value);  
		} else {  
			alert('Sorry! No Web Storage support...');  
			//console.log('Sorry! No Web Storage support...');  
		}		  
	},  
	get : function(_name){  
		//console.log(sessionStorage[_name]);  
		try{  
			return sessionStorage[_name];						  
		}catch(err){ 			  
			//console.log(err.message);			  
		}		  
	},  
	remove : function(_name){  
		sessionStorage.removeItem(_name);  
	}  
}  
  
/* --------------------------------------------------------- */  
/* ------ for prototype only, remove this at DEV ----------- */  
/* --------------------------------------------------------- */  
  
/* Dummy Status ------------------------------------ */  
/* Dummy Status ------------------------------------ */  
/* Dummy Status ------------------------------------ */  
function _resetAllDummyStatus(){  
	$('.selector').each(function(_i, _selector) {  
		_setDummyStatus($(_selector).attr('_type'), 'none');  
	});  
}  
function _setDummyStatus(_type, _value){  
	var _Selector = $(".selector[_type='"+_type+"']");  
	_Selector.val(_value).change();   
}  
function _initDummyStatus(_defaultValue){  
	  
	var _selectors = '<style>' +   
		'.ar-hide { display:none !important; }' +   
		'.selectors {' +   
			'position:fixed;' +   
			'bottom:0px;' +   
			'right:0px;' +   
			'z-index:9999;' +   
			'background-color:#FFF;' +   
			'padding:1px;' +   
			'font-size:10px;' +   
			'border:1px solid #CCC;' +   
		'}' +   
		'.selectors .selector{' +   
			'display:block;' +   
			'width:100%;' +   
			'border:1px solid #999;' +   
			'margin-bottom:1px;' +   
			'font-size:11px;' +   
		'}' +   
	'</style>' +   
//	'<div class="selectors ar-hide" >' + 		  
	'<div class="selectors ar-hide" >For Prototype Only' + 		  
		'<select class="selector" _type="_appStatus" ><option value="ignore">- appStatus -</option>' + 			  
			'<option value="01">01 Submitted</option>' +	  
			'<option value="02">02 Processing</option>' +   
			'<option value="03">03 Shortlisted</option>' +	  
			'<option value="04">04 Under Consideration</option>' +   
			'<option value="05">05 Firm Offer</option>' +	  
			'<option value="06">06 Conditional Offer</option>' +   
			'<option value="07">07 Offer-Acceptance</option>' +   
			'<option value="08">08 Withdrawn</option>' +	  
			'<option value="09">09 Con. Offer not Met</option>' +   
			'<option value="10">10 Waitlisted</option>' +   
			'<option value="11">11 Unsuccessful</option>' +   
		'</select>' + 		  
		'<select class="selector" _type="_reqType" ><option value="none">- _reqType -</option>' + 			  
			'<option value="ignore">Ignore</option>' + 		  
			'<option value="01">01</option>' +	  
			'<option value="02">02</option>' +   
			'<option value="03">03</option>' + 		  
			'<option value="04">04</option>' + 		  
			'<option value="05">05</option>' + 		  
			'<option value="06">06</option>' + 			  
			'<option value="07">07</option>' + 	  
			'<option value="08">08</option>' + 	  
			'<option value="09">09</option>' + 	  
			'<option value="10">10</option>' + 	  
			'<option value="11">11</option>' + 	  
			'<option value="12">12</option>' + 	  
			'<option value="13">13</option>' + 	  
			'<option value="14">14</option>' + 	  
			'<option value="15">15</option>' + 	  
		'</select>' +   
		'<select class="selector" _type="_logined" ><option value="ignore">- _logined -</option>' + 			  
//			'<option value="ignore">Ignore</option>' + 		   
			'<option value="false">false</option>' +	  
			'<option value="true">true</option>' +   
		'</select>' + 		  
		'<select class="selector" _type="_mode" ><option value="none">- _mode -</option>' + 			  
			'<option value="ignore">Ignore</option>' + 		  
			'<option value="create">Create</option>' +	  
			'<option value="edit">Edit</option>' +   
			'<option value="view">View</option>' + 		  
		'</select>' +   
  
/*							  
		'<select class="selector" id="_themeSwitcher" _type="_theme" ><option value="none">- Theme -</option>' + 	  
			'<option value="hku" >HKU</option>' +  
			'<option value="duud" >DUUD</option>' +  
		'</select>' +  
  
		'<select class="selector" _type="_role" >' +  
//			'<option value="ignore">=== Role ===</option>' +   
//			'<option value="ignore">Ignore</option>' +   
  
			'<option value="ad">Administrator</option>' + 			  
			'<option value="op">Normal User</option>' +   
		'</select>' + 	  
		  
		'<select class="selector" _type="_sstatus" ><option value="none">- survey status -</option>' + 			  
			'<option value="ignore">Ignore</option>' + 		  
			'<option value="inprogress">inprogress</option>' +	  
			'<option value="new">new</option>' +   
			'<option value="completed">completed</option>' + 		  
			'<option value="draft">draft</option>' + 		  
			'<option value="published">published</option>' + 		  
			'<option value="unpublished">unpublished</option>' + 			  
			'<option value="active">active</option>' + 	  
			'<option value="inactive">inactive</option>' + 	  
		'</select>' +			  
		   
		'<select class="selector" _type="_lang" ><option value="none">- lang -</option>' +  			  
			'<option value="en">English (en)</option>' +   
			'<option value="tc">繁體 (tc)</option>' +   
			'<option value="sc">简体 (sc)</option>' +   
		'</select>' +   
*/		  
	'</div>';  
	if($('.selectors').length == 0){		  
		$(document.body).prepend(_selectors);			  
	}  
	var _items = '';  
	var _type = '';  
	var _cookieValue = '';  
	function _trigger(){		  
		$('.selector').each(function(_i, _selector) {  
			_type = $(_selector).attr('_type');  
			_items = $('['+ _type +']');  
			_cookieValue = _ws.get(_type);  
			if(_cookieValue == 'null') {   
				_ws.set(_type, $(_selector).find('option').get(1).value);  
			}   
			_items.each(function(_index, _item) {  
				$(_item).removeClass('ar-hide');   
				$(_item).attr('finalShow', '');   
			});			  
		}).each(function(_i, _selector) {  
			_type = $(_selector).attr('_type');  
			$(_selector).val(_ws.get(_type));  
			_ws.set(_type, $(_selector).val());  
			_items = $('['+ _type +']');  
			_cookieValue = _ws.get(_type);  
  
			_items.each(function(_index, _item) {  
				if(_cookieValue != 'ignore'){   
					if($(_item).attr(_type) != '@'){  
						//if($(_item).attr(_type).indexOf(_cookieValue) == -1 ){  // not exist  
						var _types = $(_item).attr(_type).split(',');  
						if(jQuery.inArray(_cookieValue, _types) == -1){  // not exist  
							$(_item).attr('finalShow', 'false');  
						}	  
					}  
					if($(_item).attr(_type) == '@' && _cookieValue == 'none'){  
						$(_item).attr('finalShow', 'false');  
					}  
				}  
			});			  
		}).each(function(_i, _selector) {  
			_type = $(_selector).attr('_type');  
			_items = $('['+ _type +']');  
			_items.each(function(_index, _item) {  
				if($(_item).attr('finalShow') == 'false'){  
					$(_item).addClass('ar-hide');  
				}  
			});			  
		});	  
	}	  
	$('.selector').each(function(_i, _selector) {  
		$(_selector).change(function(e) {  
			_ws.set($(this).attr('_type'), $(this).val());			  
			_trigger();  
			//window.location.reload();  
        });;  
    });		  
	_trigger();  
	if(_ws.get('selectorShow') == 'true'){  
		$('.selectors').toggleClass('ar-hide');			  
	}  
	if(_defaultValue != undefined){  
		// alert(_defaultValue);		  
		_setDummyStatus('_role', _defaultValue);  
	}else{  
		//_setDummyStatus('_role', 'ad');  
	}  
	  
	_initThemeSwitcher();  
	  
}  
function _dummyRole(e, _this){  
	_setDummyStatus('_role', $(_this).val());  
}  
function _setTheme(_value){  
	var _links = $('link[type="text/css"]');   
	_links.each(function(index){  
		var _href = $(this).prop('href').toString();  
		var _isThemeCss = (_href.search(/resources\/primefaces-(.*?)\/theme.css/g) != -1 ? true : false );  
		if(_isThemeCss){  
			var _needChange = (_href.indexOf('primefaces-'+_value+'/theme.css') == -1 ? true : false );  
			if(_needChange){  
				$(this).prop(  
					'href',   
					_href.replace(  
						/resources\/primefaces-(.*?)\/theme.css/g,   
						'resources/primefaces-'+_value+'/theme.css'   
					)  
				);  
			}  
		}  
	});	  
}  
function _initThemeSwitcher(){  
	var _switcherExist = ($('#_themeSwitcher').length != 0 ? true : false);  
	if(_switcherExist){  
		$('#_themeSwitcher').change(function(){  
			var _value = $(this).val();  
			if(_value == 'none'){  
				_value = 'hku';  
			}  
			_setTheme(_value);  
		});  
		var _toVal = _ws.get('_theme');  
		if(_toVal == 'none' || _toVal == 'null' || _toVal == ''){  
			_toVal = 'hku';  
			_setDummyStatus('_theme', _toVal);  
		}		  
		_setTheme(_toVal);  
	}  
}  
/*  
<ul>  
<li _logined="true" >_logined="true"</li>  
<li _logined="false" >_logined="false"</li>  
<li _role="administrator" >_role="administrator"</li>  
<li _role="manager" >_role="manager"</li>  
<li _role="operator" >_role="operator"</li>  
<li _website="internal" >_website="internal" </li>  
<li _website="admin" >_website="admin" </li>  
<li _website="public" >_website="public" </li>  
<li _role="administrator" _logined="true" >_role="administrator" _logined="true" </li>  
<li _role="manager" _logined="false" >_role="manager" _logined="false"</li>  
<li _role="operator" _website="internal" >_role="operator" _website="internal" </li>  
<li _role="operator" _website="admin" >_role="operator" _website="admin" </li>  
<li _role="operator" _website="public" >_role="operator" _website="public" </li>  
</ul>  
*/  
  
/* Dummy Status ------------------------------------ */  
/* Dummy Status ------------------------------------ */  
/* Dummy Status ------------------------------------ */  
  
/* --------------------------------------------------------- */  
/* ------ for prototype only, remove this at DEV ----------- */  
/* --------------------------------------------------------- */  
  
/* --------------------------------------------------------- */  
/* ------ ngIncludePathShow, start ------------------------- */  
/* --------------------------------------------------------- */  
function _ngIncludePath(){  
	var _basePath = window.location.toString();  
	var _tmpAry = _basePath.split('/');  
	_basePath = _basePath.replace('/'+_tmpAry[_tmpAry.length-1], '').replace(_tmpAry[_tmpAry.length-2], '');   
	var _sections = $('section.ng-scope');   
	_sections.each(function(_x) {  
		var _id = '-ng-path-'+_x+'-';		  
		var _path = $(this).attr('data-ng-include');  
		_path = _path.toString().replace(/'/g, "");  
		var _div = $('<input />')  
			.prop('type', 'text')  
			.prop('value', _basePath+_path)  
			.prop('title', _basePath+_path)  
			.addClass('ng-path-dummy')			  
			.prop('id', _id);  
		if($('#'+_id).length === 0){  
			$(this).prepend(_div);			  
		}  
		_div.click(function(e){  
			$(this).select();  
			document.execCommand("copy");  
		});  
	});	  
	_sections.click(function(e){		  
		e.stopImmediatePropagation();  
		if( e.currentTarget === this ){			  
			if(e.altKey){  
				var _secPath = $(this).attr('data-ng-include');  
				_secPath = _secPath.toString().replace(/'/g, "");  
				_ngPathDummyCapture(_basePath+_secPath);  
			}  
		} 		  
	});  
	var _isShow = _ws.get('_ngIncludePathShow');  
	if(_isShow == undefined){  
		_ws.set('_ngIncludePathShow', 'false');  
		$('.ng-path-dummy').hide();  
	}else if(_isShow == 'true'){  
		_ws.set('_ngIncludePathShow', 'true');  
		$('.ng-path-dummy').show();  
	}else if(_isShow == 'false'){  
		_ws.set('_ngIncludePathShow', 'false');  
		$('.ng-path-dummy').hide();  
	}	  
}  
  
function _ngIncludePathShow(){  
	var _isShow = _ws.get('_ngIncludePathShow');  
	if(_isShow == undefined){  
		_ws.set('_ngIncludePathShow', 'false');  
		$('.ng-path-dummy').hide();  
	}else if(_isShow == 'false'){  
		_ws.set('_ngIncludePathShow', 'true');  
		$('.ng-path-dummy').show();  
	}else if(_isShow == 'true'){  
		_ws.set('_ngIncludePathShow', 'false');  
		$('.ng-path-dummy').hide();  
	}  
}  
/* --------------------------------------------------------- */  
/* ------ ngIncludePathShow, end --------------------------- */  
/* --------------------------------------------------------- */  
  
/* --------------------------------------------------------- */  
/* ------ ax-printing, start ------------------------------- */  
/* --------------------------------------------------------- */  
function _pdf(_url, _mode){	  
	$('#pdfjsIframe').remove();  
	var _b = $('body');  
	var _mask = '' +  
		'<div class="pdfjs-mask off" id="pdfjsMask" >' +   
			'<div id="pdfjsCancel" class="btn-cancel" ><i class="ti ti-close" onClick="window.pdfjsIframe._printCancel()" ></i></div>' +   
			'<div class="indicator" >' +   
				'<i class="ti ti-reload rotate-me" ></i>' +  
				'<div id="pdfjsProgress" class="progress-text" ></div>' + 				  
			'</div>' +   
		'</div>';  
	var _iframe = '<iframe class="pdfjs-iframe on" id="pdfjsIframe" name="pdfjsIframe" ></iframe>';  
	if($('#pdfjsIframe').length === 0){  
		_b.append(_iframe).append(_mask);  
	}else{  
		$('#pdfjsIframe').removeClass('off').addClass('on');  
	}  
	if(_mode === 'print'){  
		_b.addClass('pdfjs-body');  
		$('#pdfjsMask').removeClass('off').addClass('on');   
		_printProgress('0%');	  
		$('#pdfjsIframe')  
			.prop('src', 'pdfjs/viewer.html?file='+_url+'&autoPrint=true' )  
			.removeClass('on').addClass('off');		  
	}else{		  
		_b.addClass('pdfjs-body');  
		$('#pdfjsIframe')  
			.prop('src', 'pdfjs/viewer.html?file='+_url+'&autoPrint=false' )  
			.removeClass('off').addClass('on');  
	}	  
}  
function _closeIframe(_this) {	  
	$('#pdfjsIframe').removeClass('on').addClass('off');	  
	$('body').removeClass('pdfjs-body');  
}  
function _printProgress(_value){		  
	$('#pdfjsProgress').html(_value);   
}  
function _printPdfCompleted(){	  
	$('#pdfjsMask').removeClass('on').addClass('off');	  
	$('body').removeClass('pdfjs-body');  
}  
function _printPreview(){	  
	_changeView('printPreview');  
	$('.ax-printing .printer').hide();  
	$('.ax-printing .print-now').css('display', 'inline-block');  
	$('.ax-printing .print-cancel').css('display', 'inline-block');  
}  
function _printNow(){  
	window.print();  
}  
function _printCancel(){  
	_changeView('printPreview');  
	$('.ax-printing .printer').css('display', 'inline-block');;  
	$('.ax-printing .print-now').hide();  
	$('.ax-printing .print-cancel').hide();  
}  
function _changeView(_type){	  
	var _dummyClass = (_type === 'printPreview') ? 'ui-dummy-field' : 'ui-dummy-field';  
	var _bodyClass = (_type === 'printPreview') ? 'print' : 'readonly';  
	var _b = $('body');  
	var _inputs = $('input.ui-inputtext, ' +   
					'textarea.ui-inputtextarea, ' +   
					'.ui-selectcheckboxmenu, ' +   
//					'.selectOneRadio, ' +   
//					'.selectManyCheckbox, ' +   
					'.ui-selectoneradio, ' +   
					'.ui-selectmanycheckbox, ' +   
					'.ui-calendar, ' +   
					'.ui-autocomplete, ' +   
					'.ui-password, ' +   
					'.ui-inputmask, ' +   
					'div.ui-selectonemenu');  
	if(_b.hasClass(_bodyClass)){  
		if(_bodyClass === 'print'){   
			_b.removeClass('print');  
		}  
		if(_bodyClass === 'readonly'){   
			_b.removeClass('readonly');  
		}  
		if(!_b.hasClass('print') && !_b.hasClass('readonly')){  
			_inputs.show().parent().find('.'+_dummyClass).remove();  
		}		  
	}else{  
		_b.addClass(_bodyClass);	  
		_inputs.each(function(_index, _this){ 			  
			var _hasDummy = $(_this).next('.'+_dummyClass).length;						  
			var _txt = '';  
			var _vals = '';  
			var _txts = '';  
			var _tClass = '';  
			var _dummyStyle = '';  
			if(_hasDummy === 0){   
				if($(_this).hasClass('ui-selectonemenu')) {  
					var _selects = $(_this).find('select');  
					var _hasSelect = (_selects.length === 1) ? true : false;  
					if(_hasSelect){  
						_txt = $(_selects.get(0)).val();  
					}	  
				}else if($(_this).hasClass('selectOneRadio') ||  
						 $(_this).hasClass('selectManyCheckbox') ||  
						 $(_this).hasClass('ui-selectoneradio') ||  
						 $(_this).hasClass('ui-selectmanycheckbox') ||  
						$(_this).hasClass('ui-selectcheckboxmenu')   
					) {  
					  
					if($(_this).find('.ui-helper-hidden').length === 1){  
						_vals = $(_this).find('.ui-helper-hidden input:checked');	  
					}else{  
						_vals = $(_this).find('.ui-helper-hidden-accessible input:checked');	  
					}  
					_vals.each(function(_i, _t){ 					  
						_txts = _txts + $(_t).val() + ', ';  
					});	  
					_txt = _txts.substring(0, _txts.length - 2);  
				}else if($(_this).hasClass('ui-calendar') ||  
						 $(_this).hasClass('ui-autocomplete')						   
					) {  
					// field inside ui-xxxxx class  
					if($(_this).hasClass('ui-calendar')){  
						_vals = $(_this).parent().find('input');  
						_txts = '';  
						_vals.each(function(_i, _t){ 					  
							if($(_t).val() !== ''){  
								_txts = _txts + $(_t).val() + ' - ';  
							};  
						});	  
						_txt = _txts.substring(0, _txts.length - 2);  
						// hide all other calendar field	  
						$(_this).parent().find('.'+_dummyClass).hide();   
					}else{  
						_vals = $(_this).find('input');  
						_txt = _vals.val();	  
					}  
				}else {   
					_txt = $(_this).val();  
				}	  
				if($(_this).attr('style') != undefined){   
					_dummyStyle = $(_this).attr('style')  
					.replace('display: inline-block;', '')  
					.replace('display: inline;', '');  
				}  
				if(	$(_this).hasClass('ax-number-field') ){ _tClass = _tClass + 'ax-number-field' }				  
				$(_this).after('<div class=" '+_dummyClass+' '+_tClass+' " '+(_dummyStyle != '' ? 'style="'+_dummyStyle+'"' : '')+' >'+_txt+'&nbsp;</div>');	  
			}  
		}).hide();  
			// cannot hide calendar  
		//}).attr('style','display:none !important');  
	}	  
}  
/* --------------------------------------------------------- */  
/* ------ ax-printing, end --------------------------------- */  
/* --------------------------------------------------------- */  
function _initBasicAdvance(){	  
	var _pref = 'basicAdvance';   
	var _advCbx = $('.basicAdvance');  
	var _h1 = $('h1').eq(0).html().toLowerCase().replace(' ', '');  
	$(_advCbx).each(function(_x, element) {  
		if($(this).attr('transformed')){return;}		  
		if(!$(this).attr('id')) $(this).attr('id', 'id_'+_x+_pref);				  
		$(this).attr('id', $(this).attr('id')+_x+_pref+_h1);  
		var _id = $(this).attr('id');		  
		$(this).attr('id', _id + '_checkbox')  
			.addClass('hidden-checkbox')  
			.prop('checked', ((_ws.get(_id+'_checkbox') == 'checked') ? true : false))  
			.click(function(e) {			  
				if($(this).prop('checked')){ _ws.set($(this).prop('id'), 'checked'); }else{ _ws.set($(this).prop('id'), '') }   
			});	  
		var _lbls = $(this).parent().find('.basicAdvanceLabel');		  
		_lbls.attr('for', $(this).attr('id'));  
		$(this).attr('transformed', 'true');		  
    });  
}  
  
  
  
  
  
  
  
