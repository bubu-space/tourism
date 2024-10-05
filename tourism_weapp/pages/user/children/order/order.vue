<template>
	<view>
		<scroll-view scroll-x class="bg-white nav">
			<view class="flex text-center">
				<view class="cu-item flex-sub" :class="index==TabCur?'text-green cur':''" v-for="(item,index) in tabList" :key="index" @tap="tabSelect" :data-id="index">
					{{item}}
				</view>
			</view>
		</scroll-view>
		
		<view v-if="dataList.length>0" class="padding-sm">
			<view v-for="item in dataList" :key="item.id" @click="toDetail(item.id)" class="bg-white padding-sm">
				<view class="flex justify-between align-center">
					<text>下单日期：{{item.createTime}}</text>
					<text class="cu-tag sm bg-blue">{{item.status}}</text>
				</view>
				<view class="flex flex-gap-lg" style="margin: 10px 0;">
					<image :src="item.spotimage.url" style="width: 40px;height: 40px;border-radius: 2px;"></image>
					<view class="flex-sub">
						<view class="text-df">{{item.ticketName}}</view>
						<view class="text-grey margin-top-sm">游玩时间：{{item.reservationTime}}</view>
					</view>
				</view>
				<view class="flex justify-between align-center">
					<view>支付金额：<text class="text-red">￥{{item.price.toFixed(2)}}</text></view>
					<button v-if="item.status==='未评价'" @click="toComment(item.id)" class="cu-btn sm bg-orange">去评论</button>
				</view>
			</view>
		</view>
		
		<empty v-else></empty>
	</view>
</template>

<script>
	import orderApi from "@/api/orderApi.js"
	
	export default {
		data() {
			return {
				TabCur: 0,
				scrollLeft: 0,
				tabList: ['全部','已支付','未评价','已评价'],
				dataList: [],
				params: {
					current: 1,
					size: 20,
					status: null
				}
			}
		},
		onLoad() {
			this.getData()
		},
		methods: {
			getData(){
				this.params.status = this.tabList[this.TabCur]=='全部'?null:this.tabList[this.TabCur]
				orderApi.page(this.params).then(res => {
					this.dataList = res.data.records
				})
			},
			tabSelect(e) {
				this.TabCur = e.currentTarget.dataset.id;
				this.scrollLeft = (e.currentTarget.dataset.id - 1) * 60
				this.getData()
			},
			toDetail(id){
				uni.navigateTo({
					url:'/pages/user/children/order/detail?id='+id
				})
			},
			toComment(id){
				uni.navigateTo({
					url:'/pages/user/children/order/comment?orderId='+id
				})
			}
		}
	}
</script>

<style lang="scss">
	page{
		// background-color: #fff;
	}
</style>
