import request from '@/utils/http.js'

export default {
	login(data) {
	  return request({
	    url: '/user/login',
	    method: 'POST',
	    data: data,
			showLoading: true,
	  })
	},
	 
	register(data) {
	  return request({
	    url: '/user/register',
	    method: 'POST',
	    data: data,
			showLoading: true,
	  })
	},
	
	logout(data) {
	  return request({
	    url: '/user/logout',
	    method: 'POST',
	    data: data,
			showLoading: true,
	  })
	},
	
	
}




