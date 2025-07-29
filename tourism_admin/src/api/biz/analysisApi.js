import { baseRequest } from '@/utils/request'

const request = (url, ...arg) => baseRequest('/biz/dashboard/'+url, ...arg)

export default {

	getTotalData() {
		return request('view',null, 'get')
	},

	getTendData(data) {
		return request('weekview', data, 'get')
	},
}
