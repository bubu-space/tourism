import request from '@/utils/http.js'

export default {
	getUserInfo() {
	  return request({
	    url: '/user/getUserInfo',
	    method: 'GET',
			showLoading: true,
	  })
	},
	
	updateUserInfo(data){
		return request({
		  url: '/user/updateUserInfo',
		  method: 'POST',
			data: data,
			showLoading: true,
		})
	}
	
}




