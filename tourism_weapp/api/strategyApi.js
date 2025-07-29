import request from '@/utils/http.js'

export default {
	page(data) {
		return request({
			url: '/wxstrategy/getstrategy',
			method: 'GET',
			data: data,
			showLoading: true,
		})
	},

	add(data) {
		return request({
			url: '/wxstrategy/add',
			method: 'POST',
			data: data,
			showLoading: true,
		})
	},

	edit(data) {
		return request({
			url: '/wxstrategy/edit',
			method: 'POST',
			data: data,
			showLoading: true,
		})
	},


	remove(data) {
		return request({
			url: '/wxstrategy/remove',
			method: 'POST',
			data: data,
			showLoading: true,
		})
	},

	detail(data) {
		return request({
			url: '/wxstrategy/getstrategyinfo',
			method: 'GET',
			data: data,
			showLoading: true,
		})
	},

	detail2(data) {
		return request({
			url: '/wxstrategy/detail',
			method: 'GET',
			data: data,
			showLoading: true,
		})
	},

	like(data) {
		return request({
			url: '/wxstrategy/like',
			method: 'GET',
			data: data,
			showLoading: true,
		})
	},

	isuserlike(data) {
		return request({
			url: '/wxstrategy/isuserlike',
			method: 'GET',
			data: data,
			showLoading: true,
		})
	}

}