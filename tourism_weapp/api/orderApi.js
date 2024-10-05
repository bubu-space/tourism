import request from '@/utils/http.js'

export default {
	page(data) {
	  return request({
	    url: '/order/page',
	    method: 'GET',
	    data: data,
			showLoading: true,
	  })
	},
	 
	add(data) {
	  return request({
	    url: '/order/add',
	    method: 'POST',
	    data: data,
			showLoading: true,
	  })
	},
	
	addcomment(data) {
	  return request({
	    url: '/order/addcomment',
	    method: 'POST',
	    data: data,
			showLoading: true,
	  })
	},
	
	detail(data){
		return request({
		  url: '/order/detail',
		  method: 'GET',
		  data: data,
			showLoading: true,
		})
	},
	
	userinfo(data){
		return request({
		  url: '/order/userinfo',
		  method: 'GET',
		  data: data,
			showLoading: true,
		})
	},
	
	addcomment(data){
		return request({
		  url: '/order/addcomment',
		  method: 'POST',
		  data: data,
			showLoading: true,
		})
	}
	
	
}




