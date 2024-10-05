<template>
	<view>
		<view v-if="plainList.length>0" class="cu-list">
			<view 
				class="cu-item margin-bottom-sm bg-white padding-sm" :class="modalName=='move-box-'+index?'move-cur':''"
				v-for="(item,index) in plainList" :key="index"
				@touchstart="ListTouchStart" @touchmove="ListTouchMove" @touchend="ListTouchEnd" :data-target="'move-box-' + index">
				
				<view class="content flex justify-between">
					<view class="text-xl">{{item.title}}</view>
					<view v-if="item.status=='已完成'" class="cu-tag sm bg-green">已完成</view>
					<view v-if="item.status=='未完成'" class="cu-tag sm bg-orange">未完成</view>
				</view>
				<view class="action text-grey margin-top-sm">
					<text>创建时间：{{item.createTime}}</text>
				</view>
				<view class="move">
					<view class="bg-red" @click="removePlain(item.id)">删除</view>
				</view>
			</view>
		</view>
		
		<empty v-else></empty>

		<view class="cu-bar tabbar bg-white foot" style="padding: 0 10px;">
			<button class="cu-btn lg bg-blue" style="width: 100%;" @click="toDetail(null)">添加</button>
		</view>
	</view>
</template>

<script>
	import plainApi from "@/api/plainApi.js"

	export default {
		data() {
			return {
				modalName: null,
				listTouchStart: 0,
				listTouchDirection: null,
				plainList: []
			}
		},
		onShow() {
			let param = {
				pageSize: 20,
				pageNum: 1
			}
			plainApi.page(param).then(res => {
				this.plainList = res.data.records
			})
		},
		methods: {
			toDetail(id) {
				if (id) {
					uni.navigateTo({
						url: '/pages/user/children/plain/detail?id=' + id
					})
				} else {
					uni.navigateTo({
						url: '/pages/user/children/plain/detail'
					})
				}
			},
			async removePlain(id){
				await plainApi.remove({id})
				let res = await plainApi.page(param)
				this.plainList = res.data.records
			},
			// ListTouch触摸开始，获取触摸点距盒子左侧的距离
			ListTouchStart(e) {
				this.listTouchStart = e.touches[0].pageX
			},
			// ListTouch计算方向，
			ListTouchMove(e) {
				this.listTouchDirection = e.touches[0].pageX - this.listTouchStart > 0 ? 'right' : 'left'
			},
			// ListTouch计算滚动
			ListTouchEnd(e) {
				if (this.listTouchDirection == 'left') {
					this.modalName = e.currentTarget.dataset.target
				} else {
					this.modalName = null
				}
				this.listTouchDirection = null
			}
		}
	}
</script>


<style lang="scss">
	.list {
		padding: 14px;
	}
	
	.cu-list>.cu-item .move{
		bottom: 0 !important;
	}
</style>