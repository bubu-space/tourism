<template>
	<view>
		<!-- 门票名称 -->
		<view class="cu-form-group">
			<view class="title"><text class="cu-tag bg-orange">门票名称</text></view>
			<view class="text-df">{{order.ticketName}} {{order.price}}元/人</view>
		</view>
		<view class="cu-form-group">
			<view class="title"><text class="cu-tag bg-grey">门票数量</text></view>
			<view class="text-df">{{order.ticketCount}}张</view>
		</view>
		
		<!-- 订单号 -->
		<view class="cu-form-group margin-top-sm">
			<view class="title">订单号</view>
			<view class="text-grey text-df">{{order.id}}</view>
		</view>
		
		<!-- 下单日期 -->
		<view class="cu-form-group">
			<view class="title">下单日期</view>
			<view class="text-grey text-df">{{order.createTime}}</view>
		</view>
		
		<!-- 订单总金额 -->
		<view class="cu-form-group margin-top-sm">
			<view class="title">订单总金额</view>
			<view class="text-red text-df">￥{{order.price * order.ticketCount}}</view>
		</view>
		<view class="cu-form-group">
			<view class="title">实付金额</view>
			<view class="text-red text-df">￥{{order.price * order.ticketCount}}</view>
		</view>
		
		<!-- 游玩日期 -->
		<view class="cu-form-group margin-top-sm">
			<view class="title">游玩日期</view>
			<view class="text-grey text-df">{{order.reservationTime}}</view>
		</view>
		
		<view>
			<view class="text-df text-center" style="margin: 20px 0 10px 0;">游客信息</view>
			<view v-for="item in userInfo" :key="item.id">
				<view class="cu-form-group margin-top-sm">
					<view class="title">姓名</view>
					<view class="text-grey text-df">{{item.userName}}</view>
				</view>
				<view class="cu-form-group">
					<view class="title">手机号</view>
					<view class="text-grey text-df">{{item.userPhone}}</view>
				</view>
				<view class="cu-form-group">
					<view class="title">身份证号</view>
					<view class="text-grey text-df">{{item.userIdCard}}</view>
				</view>
			</view>
		</view>
		
	</view>
</template>

<script>
	import orderApi from "@/api/orderApi.js"
	
	export default {
		data() {
			return {
				order: [],
				userInfo: []
			};
		},
		onLoad(option) {
			orderApi.detail({id: option.id}).then(res => {
				this.order = res.data
			})
			orderApi.userinfo({orderId: option.id}).then(res => {
				this.userInfo = res.data
			})
		},
		methods: {
			
		}
	}
</script>



<style lang="scss">

</style>
