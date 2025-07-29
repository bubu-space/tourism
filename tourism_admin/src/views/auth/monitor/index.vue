<template>
	<analyse />
	<a-card :bordered="false" :body-style="{ 'padding-top': '10px' }">
		<a-tabs v-model:activeKey="activeKey">
			<a-tab-pane key="1" tab="B端会话">
				<monitor-b-tab />
			</a-tab-pane>
		</a-tabs>
	</a-card>
</template>

<script setup name="authMonitor">
	import Analyse from './analyse.vue'
	import MonitorBTab from './bTab.vue'
	const activeKey = ref('1')
</script>
