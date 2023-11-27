package sv.ugm.sensormobile.ui.designsystem.component

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.DrawerState
import androidx.compose.material3.Icon
import androidx.compose.material3.ModalDrawerSheet
import androidx.compose.material3.ModalNavigationDrawer
import androidx.compose.material3.NavigationDrawerItem
import androidx.compose.material3.NavigationDrawerItemDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch
import sv.ugm.sensormobile.ui.util.NavDrawerItem
import sv.ugm.sensormobile.ui.util.load

@Composable
fun NavDrawer(
    modifier: Modifier = Modifier,
    drawerState: DrawerState,
    scope: CoroutineScope,
    itemList: List<NavDrawerItem>,
    onItemSelected: (NavDrawerItem) -> Unit,
    gestureEnabled: Boolean = true,
    content: @Composable () -> Unit,
) {
    val selectedItem = remember { mutableStateOf(itemList[0]) }
    
    ModalNavigationDrawer(
        drawerState = drawerState,
        gesturesEnabled = gestureEnabled,
        drawerContent = {
            ModalDrawerSheet {
                LazyColumn(
                    contentPadding = PaddingValues(
                        horizontal = 0.dp,
                        vertical = 16.dp,
                    ),
                ) {
                    items(
                        items = itemList,
                        key = { item -> item.title },
                    ) { item ->
                        NavigationDrawerItem(
                            icon = {
                                Icon(
                                    imageVector = item.icon,
                                    contentDescription = null,
                                )
                            },
                            label = {
                                Text(text = item.title.load())
                            },
                            selected = item == selectedItem.value,
                            onClick = {
                                scope.launch { drawerState.close() }
                                onItemSelected(item)
                                selectedItem.value = item
                            },
                            modifier = Modifier
                                .padding(NavigationDrawerItemDefaults.ItemPadding),
                        )
                    }
                }
            }
        },
        modifier = modifier,
    ) {
        content()
    }
}