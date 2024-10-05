<template>
	<view>
		<!-- 轮播图 -->
		<swiper class="screen-swiper" :indicator-dots="true" :circular="true" :autoplay="true" interval="3000" duration="500">
			<swiper-item v-for="(item,index) in spot.images" :key="index">
				<image :src="item.url" mode="aspectFill"></image>
			</swiper-item>
		</swiper>

		<!-- 通知 -->
		<view>
			<uni-notice-bar show-icon scrollable color="#ffaa00" :text="notice" />
		</view>

		<view class="cu-form-group">
			<view class="title"><text class="cu-tag bg-brown">门票名称</text></view>
			<view class="text-xl">{{ticket.name}}</view>
		</view>

		<view class="cu-form-group">
			<view class="title"><text class="cu-tag bg-orange">门票单价</text></view>
			<view class="text-red"><text class="text-xl">￥{{ticket.price}}</text>元/人</view>
		</view>

		<view class="margin-top">
			<view class="bg-white padding-sm text-df text-center">订票信息</view>
			<view class="cu-form-group">
				<view class="title"><text class="cu-tag bg-blue">游玩日期</text></view>
				<uni-datetime-picker type="date" v-model="formData.reservationTime" @change="datePickerShow=false" @show="datePickerShow=true" @maskClick="datePickerShow=false" />
			</view>

			<view class="cu-form-group">
				<view class="title"><text class="cu-tag bg-blue">购票人数</text></view>
				<picker @change="PickerChange" :value="index" :range="[1,2,3,4,5,6,7,8,9,10]">
					<view class="picker">
						{{formData.ticketCount>0?formData.ticketCount+'人':'请选择购片人数'}}
					</view>
				</picker>
			</view>
		</view>

		<view class="margin-top" style="padding-bottom: 100px;">
			<view class="bg-white padding-sm text-df text-center">游客信息</view>
			<view v-for="(item,index) in formData.userTickInfos" :key="index">
				<view class="padding-sm text-df">游客{{index+1}}</view>
				<view class="cu-form-group">
					<view class="title">游客姓名</view>
					<input placeholder="输入游客姓名" type="text" v-model:value="item.userName"></input>
				</view>
				<view class="cu-form-group">
					<view class="title">游客电话</view>
					<input placeholder="输入游客电话" type="number" v-model:value="item.userPhone"></input>
				</view>
				<view class="cu-form-group">
					<view class="title">身份证号</view>
					<input placeholder="输入游客身份证号" type="number" v-model:value="item.userIdCard"></input>
				</view>
			</view>
		</view>

		<view v-if="!datePickerShow" class="cu-bar bg-white tabbar border shop foot">
			<view class="text-xl text-red" style="padding: 0 20px;">
				总价：￥{{this.formData.price.toFixed(2)}}
			</view>
			<view @click="onSubmit" class="bg-red submit">立即订购</view>
		</view>

	</view>
</template>



<script>
	import indexApi from "@/api/indexApi.js"
	import orderApi from "@/api/orderApi.js"
	
	export default {
		data() {
			return {
				datePickerShow: false,
				swiperList: [],
				notice: '请在开园时间范围内游玩，游玩时注意人身财产安全。',
				spot: {},
				ticket: {},
				formData: {
					price: 0,
					ticketCount: 1,
					payMode: '小程序',
					ticketId: null,
					spotName: null,
					ticketName: null,
					reservationTime: null,
					userTickInfos: [
						{
							userName: null,
							userPhone: null,
							userIdCard: null
						}
					]
				}
			}
		},
		onLoad(option) {
			let {spotId,ticketId} = option
			indexApi.getspotinfo({spotId}).then(res => {
				this.spot = res.data
				this.formData.spotName = res.data.spotName
			})
			indexApi.wxticketInfo({id:ticketId}).then(res => {
				this.ticket = res.data
				this.formData.price = res.data.price
				this.formData.ticketId = res.data.id
				this.formData.ticketName = res.data.name
			})
		},
		methods: {
			PickerChange(e){
				this.formData.ticketCount = Number.parseInt(e.detail.value) + 1
			},
			onSubmit(){
				orderApi.add(this.formData).then(res => {
					uni.showToast({
						icon:'success',
						title:'下单成功',
						success: () => {
							uni.navigateTo({
								url:'/pages/user/children/order/order'
							})
						}
					})
				})
			}
		},
		watch: {
			"formData.ticketCount": {
				handler(newVal,oldVal){
					// 添加人员信息
					this.formData.userTickInfos = []
					for (var i = 0; i < newVal; i++) {
						this.formData.userTickInfos.push({
							userName: null,
							userPhone: null,
							userIdCard: null
						})
					}
					// 计算价格
					this.formData.price = this.ticket.price * newVal
				}
			}
		}
	}
</script>



<style lang="scss">
	.uni-date-x--border {
		border: none !important;
	}
</style>