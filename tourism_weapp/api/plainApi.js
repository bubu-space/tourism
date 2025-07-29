import request from '@/utils/http.js'

export default {
	page(data) {
	  return request({
	    url: '/plan/getplan',
	    method: 'GET',
	    data: data,
			showLoading: true,
	  })
	},
	 
	detail(data) {
	  return request({
	    url: '/plan/getplanbyid',
	    method: 'GET',
	    data: data,
			showLoading: true,
	  })
	},
	
	saveplan(data) {
	  return request({
	    url: '/plan/saveplan',
	    method: 'POST',
	    data: data,
			showLoading: true,
	  })
	},
	
	edit(data) {
	  return request({
	    url: '/plan/updateplan',
	    method: 'POST',
	    data: data,
			showLoading: true,
	  })
	},
	
	remove(data){
		return request({
		  url: '/plan/deleteplan',
		  method: 'GET',
		  data: data,
			showLoading: true,
		})
	}
	
}




