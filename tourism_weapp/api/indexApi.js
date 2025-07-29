import request from '@/utils/http.js'

export default {
	getredspot() {
		return request({
			url: '/index/getredspot',
			method: 'GET',
			showLoading: true,
		})
	},

	getnewplan() {
		return request({
			url: '/index/getnewplan',
			method: 'GET',
			showLoading: true,
		})
	},

	getredstrategy() {
		return request({
			url: '/index/getredstrategy',
			method: 'GET',
			showLoading: true,
		})
	},

	getspotactivite() {
		return request({
			url: '/index/getspotactivite',
			method: 'GET',
			showLoading: true,
		})
	},

	getactiviteinfo(data) {
		return request({
			url: '/index/getactiviteinfo',
			method: 'GET',
			data: data,
			showLoading: true,
		})
	},

	getuserplan() {
		return request({
			url: '/index/getuserplan',
			method: 'GET',
			showLoading: true,
		})
	},

	getrecommend(data) {
		return request({
			url: '/index/getrecommend',
			method: 'GET',
			data: data,
			showLoading: true,
		})
	},

	getRecommendInfo(data) {
		return request({
			url: '/recommend/info',
			method: 'GET',
			data: data,
			showLoading: true,
		})
	},

	getconfig(data) {
		return request({
			url: '/index/getconfig',
			method: 'GET',
			data: data,
			showLoading: true,
		})
	},

	getheritage() {
		return request({
			url: '/index/getheritage',
			method: 'GET',
			showLoading: true,
		})
	},

	getheritagedetail(data) {
		return request({
			url: '/index/getheritagedetail',
			method: 'GET',
			data: data,
			showLoading: true,
		})
	},

	getspotbook(data) {
		return request({
			url: '/index/getspotbook',
			method: 'GET',
			data: data,
			showLoading: true,
		})
	},

	getspotinfo(data) {
		return request({
			url: '/spot/getspotinfo',
			method: 'GET',
			data: data,
			showLoading: true,
		})
	},

	getspotheritage(data) {
		return request({
			url: '/spot/getspotheritage',
			method: 'GET',
			data: data,
			showLoading: true,
		})
	},

	getticket(data) {
		return request({
			url: '/spot/getticket',
			method: 'GET',
			data: data,
			showLoading: true,
		})
	},

	getcomment(data) {
		return request({
			url: '/spot/getcomment',
			method: 'GET',
			data: data,
			showLoading: false,
		})
	},

	wxticketInfo(data) {
		return request({
			url: '/wxticket/info',
			method: 'GET',
			data: data,
			showLoading: false,
		})
	}





}